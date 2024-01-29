package com.luisz.lutz.entity

import com.luisz.lutz.game.team.Team

abstract class TeamEntity(val team: Team){
    protected fun removeFromTeam(){
        team.removeEntity(this)
    }

    abstract fun updateSecond()
}