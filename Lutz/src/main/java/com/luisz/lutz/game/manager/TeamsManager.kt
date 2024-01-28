package com.luisz.lutz.game.manager

import com.luisz.lutz.game.ILutzGame
import com.luisz.lutz.game.profile.GamePlayerProfile
import com.luisz.lutz.game.team.Team
import com.luisz.lutz.game.team.TeamColor
import com.luisz.lutz.game.team.data.TeamData
import com.luisz.lutz.message.Message
import org.bukkit.entity.Player

class TeamsManager(val game: ILutzGame) {
    private val teams = HashMap<TeamColor, Team>()

    private fun register(data: TeamData): Boolean{
        if(!contains(data.color)){
            teams[data.color] = Team(game, data)
            return true
        }
        return false
    }

    fun amountOfPlayers(): Int{
        var a = 0
        for(t in teams.values){
            a += t.size()
        }
        return a
    }

    fun isPlayer(player: Player): Boolean{
        for(t in teams.values){
            if(t.contains(player)){
                return true
            }
        }
        return false
    }

    fun contains(color: TeamColor): Boolean{
        return teams.keys.contains(color)
    }
    fun contains(team: Team): Boolean{
        return teams.values.contains(team)
    }

    fun getTeamByColor(color: TeamColor): Team?{
        return teams[color]
    }

    fun getPlayerTeam(player: Player): Team?{
        for(t in teams){
            if(t.value.contains(player)){
                return t.value
            }
        }
        return null
    }

    fun joinInTeam(teamColor: TeamColor, player: Player): GamePlayerProfile?{
        val team = teams[teamColor]
        if(team != null){
            val profile = GamePlayerProfile(game, player)
            if(team.join(profile)){
                return profile
            }
        }
        return null
    }
    fun joinRandom(player: Player): GamePlayerProfile{
        val profile = GamePlayerProfile(game, player)
        for(t in teams){
            if(!t.value.isFull() && t.value.join(profile)){
                break
            }
        }
        return profile
    }

    fun broadcastMessage(message: Message, vararg vars: String){
        for(t in teams.values){
            t.broadcastMessage(message, *vars)
        }
    }

    fun buildTeamsFromData(teamsData: List<TeamData>) {
        for(td in teamsData){
            register(td)
        }
    }
}