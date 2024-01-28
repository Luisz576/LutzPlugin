package com.luisz.lutz.game.kingdom

import org.bukkit.Location

class Kingdom(private val kingSpawn: Location, private val playerSpawn: Location) {
    fun kingSpawn(): Location{
        return kingSpawn
    }

    fun playerSpawn(): Location{
        return playerSpawn
    }
}