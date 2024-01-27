package com.luisz.lutz.game.profile.reason

import com.luisz.lutz.events.player.PlayerDieGameEvent
import com.luisz.lutz.game.ILutzGame
import com.luisz.lutz.game.profile.GamePlayerProfile
import org.bukkit.entity.Entity

class PlayerDieReason private constructor(val finalKill: Boolean, val who: GamePlayerProfile, reason: PlayerDeathReason, val killer: Entity?) {
    fun createEvent(game: ILutzGame): PlayerDieGameEvent {
        return PlayerDieGameEvent(game, this)
    }

    companion object {
        fun voidReason(finalKill: Boolean, who: GamePlayerProfile): PlayerDieReason{
            return PlayerDieReason(finalKill, who, PlayerDeathReason.VOID, null)
        }

        fun enemyReason(finalKill: Boolean, who: GamePlayerProfile, killer: Entity?): PlayerDieReason{
            return PlayerDieReason(finalKill, who, PlayerDeathReason.ENEMY, killer)
        }

        fun disconnectReason(finalKill: Boolean, who: GamePlayerProfile): PlayerDieReason{
            return PlayerDieReason(finalKill, who, PlayerDeathReason.DISCONNECT, null)
        }

        fun unknownReason(finalKill: Boolean, who: GamePlayerProfile, killer: Entity?): PlayerDieReason{
            return PlayerDieReason(finalKill, who, PlayerDeathReason.UNKNOWN, killer)
        }
    }
}