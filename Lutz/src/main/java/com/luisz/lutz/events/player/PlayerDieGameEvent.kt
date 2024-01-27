package com.luisz.lutz.events.player

import com.luisz.lutz.events.GameEvent
import com.luisz.lutz.game.ILutzGame
import com.luisz.lutz.game.profile.reason.PlayerDieReason

class PlayerDieGameEvent(game: ILutzGame, val reason: PlayerDieReason) : GameEvent(game){
    fun finalKill(): Boolean{
        return reason.finalKill
    }
}