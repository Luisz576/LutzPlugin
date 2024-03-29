package com.luisz.lutz.game.team

import com.luisz.lutz.entity.king.King
import com.luisz.lutz.entity.RendableEntity
import com.luisz.lutz.entity.TeamEntity
import com.luisz.lutz.game.ILutzGame
import com.luisz.lutz.game.profile.GamePlayerProfile
import com.luisz.lutz.game.team.data.TeamData
import com.luisz.lutz.game.team.entity.TeamEntitiesManager
import com.luisz.lutz.message.Message
import org.bukkit.entity.Player

class Team(val game: ILutzGame, data: TeamData) {
    val color = data.color
    private val max: Int = data.max
    val kingdom = data.kingdom

    private val members = ArrayList<GamePlayerProfile>()
    private var king: King? = null

    private val teamEntitiesManager = TeamEntitiesManager(this)

    fun forEachMember(consumer: (member: GamePlayerProfile) -> Unit){
        for(m in members){
            consumer.invoke(m)
        }
    }

    fun size(): Int{
        return members.size
    }

    fun isFull(): Boolean{
        return members.size >= max
    }

    fun contains(profile: GamePlayerProfile): Boolean{
        for(m in members){
            if(m == profile){
                return true
            }
        }
        return false
    }
    fun contains(player: Player): Boolean{
        for(m in members){
            if(m.player == player){
                return true
            }
        }
        return false
    }

    fun join(profile: GamePlayerProfile): Boolean{
        if(members.size < max){
            members.add(profile)
            profile.makeAPlayerOf(this)
            return true
        }
        return false
    }

    fun quit(profile: GamePlayerProfile){
        if(members.remove(profile)){
            profile.playerRemovedFromTeam()
        }
    }

    fun onKingDie(king: King){
        if(this.king == king){
            // TODO: send title
            removeEntity(king)
            this.king = null
        }
    }

    fun hasKing(): Boolean {
        return king != null
    }

    fun broadcastMessage(message: Message, vararg vars: String) {
        for(m in members){
            m.sendMessage(message, *vars)
        }
    }

    fun joinTeamEntity(teamEntity: TeamEntity){
        teamEntitiesManager.register(teamEntity)
        if(teamEntity is RendableEntity){
            game.registerRendableEntity(teamEntity)
        }
    }

    fun isTeamEntity(teamEntity: TeamEntity): Boolean{
        return teamEntitiesManager.contains(teamEntity)
    }

    fun removeEntity(teamEntity: TeamEntity) {
        if(teamEntitiesManager.remove(teamEntity)){
            if(teamEntity is RendableEntity){
                game.unregisterRendableEntity(teamEntity)
            }
        }
    }
}