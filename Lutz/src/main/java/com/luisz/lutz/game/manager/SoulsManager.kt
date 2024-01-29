package com.luisz.lutz.game.manager

import com.luisz.lutz.game.ILutzGame
import com.luisz.lutz.game.profile.GamePlayerProfile
import com.luisz.lutz.util.SecondUpdater
import java.util.function.Consumer

class SoulsManager(val game: ILutzGame, private val timeToRespawn: Int) : SecondUpdater {
    private val spectators = ArrayList<GamePlayerProfile>()
    private val souls = HashMap<GamePlayerProfile, Int>()

    fun doSpectator(profile: GamePlayerProfile){
        profile.makeASpectator()
        souls.remove(profile)
        spectators.add(profile)
    }

    override fun updateSecond(){
        val toRespawn = ArrayList<GamePlayerProfile>()
        for(s in souls){
            souls[s.key] = s.value - 1
            if(souls[s.key]!! <= 0){
                toRespawn.add(s.key)
            }
        }
        for(tr in toRespawn){
            respawn(tr)
        }
    }
    private fun respawn(profile: GamePlayerProfile){
        profile.respawn()
        souls.remove(profile)
        spectators.remove(profile)
    }

    fun scheduleToRespawn(profile: GamePlayerProfile) {
        if (!souls.containsKey(profile)) {
            souls[profile] = timeToRespawn
        }
    }

    fun free() {
        for(s in spectators){
            game.quit(s.player)
        }
        for(s in souls){
            game.quit(s.key.player)
        }
    }

    fun forEachSpectator(consumer: Consumer<GamePlayerProfile>) {
        spectators.forEach(consumer)
    }
}