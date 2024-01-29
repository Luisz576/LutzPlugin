package com.luisz.lutz.game.kingdom

import com.luisz.lutz.building.mine.Generator
import com.luisz.lutz.util.SecondUpdater
import org.bukkit.Location

class Kingdom(private val kingSpawn: Location, private val playerSpawn: Location, private val house: Generator, private val ironMine: Generator, private val diamondMine: Generator) : SecondUpdater {
    fun kingSpawn(): Location{
        return kingSpawn
    }

    fun playerSpawn(): Location{
        return playerSpawn
    }

    fun house(): Generator{
        return house
    }
    fun ironMine(): Generator{
        return ironMine
    }
    fun diamondMine(): Generator{
        return diamondMine
    }

    override fun updateSecond() {
        ironMine.updateSecond()
        house.updateSecond()
        diamondMine.updateSecond()
    }

    fun reset(){
        ironMine.reset()
        house.reset()
        diamondMine.reset()
    }
}