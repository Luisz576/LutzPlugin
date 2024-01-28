package com.luisz.lutz.arena

import com.luisz.lutz.game.team.data.TeamData
import org.bukkit.Location

data class Arena(val id: String, val name: String, val soulsSpawn: Location, val teams: List<TeamData>){
    fun teamsSize(): Int{
        return teams.size
    }
}