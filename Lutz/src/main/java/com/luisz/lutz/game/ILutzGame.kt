package com.luisz.lutz.game

import com.luisz.lapi.game.IGame
import com.luisz.lutz.arena.Arena
import com.luisz.lutz.game.manager.rendableentities.IRendableEntitiesManager
import com.luisz.lutz.game.properties.ILutzGameProperties
import org.bukkit.Location

abstract class ILutzGame(properties: ILutzGameProperties) : IGame(), IRendableEntitiesManager {
    private val minPlayersByTeam = properties.minPlayersByTeam()
    fun minPlayersByTeam(): Int{
        return minPlayersByTeam
    }
    private val minPlayers = properties.minPlayersByTeam() * properties.arena().teamsSize()
    fun minPlayers(): Int{
        return minPlayers
    }
    private val arena = properties.arena()
    protected fun arena(): Arena{
        return arena
    }

    private var state: GameState = GameState.STARTING
    fun state(): GameState{
        return state
    }
    protected fun setState(state: GameState){
        this.state = state
    }

    override fun isOpen(): Boolean {
        return state().isOpenState
    }

    fun soulsSpawn(): Location{
        return arena.soulsSpawn
    }

    abstract fun amountOfPlayers(): Int
    abstract fun timeInSeconds(): Int
}