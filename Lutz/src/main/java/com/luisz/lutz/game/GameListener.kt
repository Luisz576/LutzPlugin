package com.luisz.lutz.game

import com.luisz.lutz.entity.king.King
import com.luisz.lutz.events.king.KingDieGameEvent
import com.luisz.lutz.events.player.PlayerDisconnectGameEvent
import com.luisz.lutz.events.player.PlayerJoinGameEvent
import com.luisz.lutz.events.player.PlayerReconnectGameEvent
import com.luisz.lutz.game.profile.role.Role
import com.luisz.lutz.message.LutzMessages
import com.luisz.lutz.message.MessageKeys
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.util.function.Consumer

class GameListener(val game: ILutzGame, private val onKingDie: Consumer<King>) : Listener {
    private fun isGame(g: ILutzGame): Boolean{
        return game == g
    }

    @EventHandler
    fun onPlayerReconnectGameEvent(e: PlayerReconnectGameEvent){
        if(isGame(e.game)){
            game.renderRendableEntitiesTo(e.profile.player)
            game.broadcastMessage(LutzMessages.getMessage(MessageKeys.PLAYER_RECONNECT)!!, e.profile.player.displayName)
        }
    }

    @EventHandler
    fun onPlayerDisconnectGameEvent(e: PlayerDisconnectGameEvent){
        if(isGame(e.game)){
            game.broadcastMessage(LutzMessages.getMessage(MessageKeys.PLAYER_DISCONNECT)!!)
        }
    }

    @EventHandler
    fun onPlayerJoinGameEvent(e: PlayerJoinGameEvent){
        if(isGame(e.game)){
            game.broadcastMessage(
                LutzMessages.getMessage(
                    if(e.profile.role() == Role.PLAYER) MessageKeys.PLAYER_JOIN_IN_GAME_LIKE_PLAYER else MessageKeys.PLAYER_JOIN_IN_GAME_LIKE_SPECTATOR
                )!!,
                e.profile.player.displayName
            )
        }
    }

    @EventHandler
    fun onKingDieGameEvent(e: KingDieGameEvent){
        if(isGame(e.game)){
            onKingDie.accept(e.king)
            game.broadcastMessage(
                LutzMessages.getMessage(MessageKeys.KING_IS_KILLED)!!,
                "${e.king.team.color.color}${e.king.team.color.name.uppercase()}"
            )
            // TODO: animation
        }
    }

    // TODO: reconnect
    // TODO: disconnect
}