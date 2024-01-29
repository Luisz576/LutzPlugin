package com.luisz.lutz.game

import com.luisz.lutz.entity.King
import com.luisz.lutz.events.king.KingDieGameEvent
import com.luisz.lutz.events.player.PlayerReconnectGameEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.util.function.Consumer

class GameListener(val game: ILutzGame, private val onKingDie: Consumer<King>) : Listener {
    fun isGame(g: ILutzGame): Boolean{
        return game == g
    }

    @EventHandler
    fun onPlayerReconnectGameEvent(e: PlayerReconnectGameEvent){
        if(isGame(e.game)){
            game.renderRendableEntitiesTo(e.profile.player)
        }
    }

    @EventHandler
    fun onKingDieGameEvent(e: KingDieGameEvent){
        if(isGame(e.game)){
            onKingDie.accept(e.king)
            // TODO: animation
        }
    }

    // TODO: reconnect
    // TODO: disconnect
}