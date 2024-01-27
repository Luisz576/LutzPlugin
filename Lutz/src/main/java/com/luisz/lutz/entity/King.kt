package com.luisz.lutz.entity

import com.luisz.lapi.npc.npcs.HumanNPC
import com.luisz.lapi.npc.npcs.options.HumanNpcOptions
import com.luisz.lapi.player.skin.Skin
import com.luisz.lutz.events.king.KingDieGameEvent
import com.luisz.lutz.game.team.Team
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

class King(val team: Team) {
    companion object {
        const val MAX_LIFE = 1000f
    }

    private var entity: HumanNPC? = null
    private var life = MAX_LIFE

    fun isSpawned(): Boolean{
        return entity != null && entity!!.isSpawned
    }

    fun spawn(loc: Location){
        if(isSpawned()){
            return
        }

        entity = HumanNPC.build(HumanNpcOptions.Builder()
            .showNameTag()
            .name("King")
            .setCollidable()
            .skin(Skin.fromName("Luisz576"))
            .build())

        entity!!.spawn(loc)
    }

    fun damage(damager: Entity, d: Float){
        if(isSpawned() && canDamage(damager)){
            life -= d
            // doDamageAnimation()
            if(life <= 0){
                kill()
            }
        }
    }

    fun canDamage(damager: Entity): Boolean{
        if(damager is Player){
            return !team.contains(damager)
        }
        // TODO: else if() <- see if it is a defencer or attacker or ...
        return false
    }

    private fun kill(){
        entity!!.remove()
        Bukkit.getPluginManager().callEvent(KingDieGameEvent(team.game, this))
    }
}