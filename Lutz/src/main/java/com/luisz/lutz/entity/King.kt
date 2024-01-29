package com.luisz.lutz.entity

import com.luisz.lapi.npc.npcs.HumanNPC
import com.luisz.lapi.npc.npcs.options.HumanNpcOptions
import com.luisz.lapi.player.skin.Skin
import com.luisz.lutz.entity.attribute.KingAttributes
import com.luisz.lutz.events.king.KingDieGameEvent
import com.luisz.lutz.game.team.Team
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

class King(team: Team) : TeamEntity(team) {
    companion object {
        const val MAX_LIFE = 50f
    }

    private val kingAttributes = ArrayList<KingAttributes.Companion.KingAttribute>()

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

        team.joinTeamEntity(this)
    }

    fun addModifier(attribute: KingAttributes.Companion.KingAttribute){
        if(!hasAttribute(attribute.type)) {
            kingAttributes.add(attribute)
            updateAttributes()
        }
    }
    fun hasAttribute(type: KingAttributes.Companion.KingAttributeType): Boolean{
        for(a in kingAttributes){
            if(a.type == type){
                return true
            }
        }
        return false
    }
    private fun updateAttributes(){
        // TODO: send packets
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

    override fun updateSecond() {
        kingAttributes.forEach {
            if(it.itSelfRegenerates()){
                life += it.secondSelfRegenerate()
            }
            if(it.itRegeneratesNextTeamMembers()){
                val regenerate = it.secondRegenerateNextTeamMembers()
                team.forEachMember { m ->
                    m.player.health += regenerate
                }
            }
        }
    }

    private fun kill(){
        entity!!.remove()
        Bukkit.getPluginManager().callEvent(KingDieGameEvent(team.game, this))
        kingAttributes.forEach {
            it.death()
        }
        kingAttributes.clear()
        removeFromTeam()
    }
}