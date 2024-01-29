package com.luisz.lutz.game.team.entity

import com.luisz.lutz.entity.TeamEntity
import com.luisz.lutz.game.team.Team

class TeamEntitiesManager(val team: Team) {
    private val entities = ArrayList<TeamEntity>()

    fun register(entity: TeamEntity){
        if(!entities.contains(entity)) {
            entities.add(entity)
        }
    }

    fun contains(entity: TeamEntity): Boolean{
        return entities.contains(entity)
    }

    fun remove(teamEntity: TeamEntity) {
        entities.remove(teamEntity)
    }
}