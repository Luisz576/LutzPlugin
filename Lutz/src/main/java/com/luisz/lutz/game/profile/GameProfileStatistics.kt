package com.luisz.lutz.game.profile

class GameProfileStatistics {
    private var kills = 0
    fun kills(): Int {
        return kills
    }
    fun addKill(){
        kills++
    }

    private var finalKills = 0
    fun finalKills(): Int {
        return finalKills
    }
    fun addFinalKills(){
        finalKills++
    }
}