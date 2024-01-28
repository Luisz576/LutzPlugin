package com.luisz.lutz.game.properties

import com.luisz.lutz.arena.Arena
import com.luisz.lutz.exception.InvalidGamePropertiesException

class GameProperties(private val recruitingTime: Int, private val stoppingTime: Int, private val timeToRespawn: Int, private val minPlayersByTeam: Int, private val arena: Arena): ILutzGameProperties {
    override fun arena(): Arena {
        return arena
    }

    fun recruitingTime(): Int {
        return recruitingTime
    }

    fun stoppingTime(): Int{
        return stoppingTime
    }

    fun timeToRespawn(): Int{
        return timeToRespawn
    }

    override fun minPlayersByTeam(): Int{
        return minPlayersByTeam
    }

    companion object {
        class Builder{
            private var arena: Arena? = null
            fun arena(arena: Arena): Builder {
                this.arena = arena
                return this
            }

            private var recruitingTime: Int = -1
            fun recruitingTime(t: Int): Builder{
                this.recruitingTime = t
                return this
            }

            private var stoppingTime: Int = -1
            fun stoppingTime(t: Int): Builder{
                this.stoppingTime = t
                return this
            }

            private var timeToRespawn: Int = -1
            fun timeToRespawn(t: Int): Builder{
                this.timeToRespawn = t
                return this
            }

            private var minPlayersByTeam: Int = -1
            fun minPlayersByTeam(t: Int): Builder{
                this.minPlayersByTeam = t
                return this
            }

            fun build(): GameProperties {
                val errors = ArrayList<String>()

                if(arena == null){
                    errors.add("'Arena' is null")
                }

                if(recruitingTime <= 0){
                    errors.add("'recruitingTime' is invalid")
                }

                if(stoppingTime <= 0){
                    errors.add("'stoppingTime' is invalid")
                }

                if(minPlayersByTeam <= 0){
                    errors.add("'minPlayersByTeam' is invalid")
                }

                if(timeToRespawn <= 0){
                    errors.add("'timeToRespawn' is invalid")
                }

                if(errors.isNotEmpty()) {
                    throw InvalidGamePropertiesException(errors)
                }
                return GameProperties(recruitingTime, stoppingTime, timeToRespawn, minPlayersByTeam, arena!!)
            }
        }
    }
}