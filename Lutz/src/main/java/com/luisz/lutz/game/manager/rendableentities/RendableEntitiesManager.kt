package com.luisz.lutz.game.manager.rendableentities

import com.luisz.lutz.entity.RendableEntity
import com.luisz.lutz.game.manager.SoulsManager
import com.luisz.lutz.game.team.TeamsManager
import org.bukkit.entity.Player

class RendableEntitiesManager(private val teamsManager: TeamsManager, private val soulsManager: SoulsManager) : IRendableEntitiesManager {
    private val rendableEntities = ArrayList<RendableEntity>()
    override fun registerRendableEntity(rendableEntity: RendableEntity) {
        if(!rendableEntities.contains(rendableEntity)){
            rendableEntities.add(rendableEntity)
            rendableEntity.registerRenderListener(this::entityRenderCallback)
        }
    }

    override fun unregisterRendableEntity(rendableEntity: RendableEntity) {
        if(rendableEntities.remove(rendableEntity)){
            rendableEntity.unregisterRenderListener()
        }
    }

    fun unregisterAll(){
        for(re in rendableEntities) {
            re.unregisterRenderListener()
        }
        rendableEntities.clear()
    }

    override fun renderRendableEntitiesTo(player: Player) {
        rendableEntities.forEach {
            it.renderTo(player)
        }
    }

    private fun entityRenderCallback(rendableEntity: RendableEntity){
        teamsManager.forEachTeam {
            it.forEachMember { member ->
                rendableEntity.renderTo(member.player)
            }
        }
        soulsManager.forEachSpectator {
            rendableEntity.renderTo(it.player)
        }
    }
}