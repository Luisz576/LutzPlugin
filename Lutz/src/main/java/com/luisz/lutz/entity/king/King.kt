package com.luisz.lutz.entity.king

import com.luisz.lapi.common.tuple.Tuple
import com.luisz.lapi.npc.npcs.HumanNPC
import com.luisz.lapi.npc.npcs.options.HumanNpcOptions
import com.luisz.lapi.player.skin.Skin
import com.luisz.lutz.entity.RendableEntity
import com.luisz.lutz.entity.TeamEntity
import com.luisz.lutz.events.king.KingDieGameEvent
import com.luisz.lutz.game.team.Team
import com.luisz.lutz.util.ArmorSet
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer

class King(team: Team) : TeamEntity(team), RendableEntity {
    companion object {
        const val MAX_LIFE = 50f
    }

    private val kingAttributes = ArrayList<KingAttributes.Companion.KingAttribute>()

    private var listenerToRender: Consumer<RendableEntity>? = null

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

        updateAttributes()
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
        if(isSpawned()) {
            var itemMainHand = Tuple<ItemStack?, Int>(null, 0)
            var armor = Tuple<ArmorSet?, Int>(null, 0)
            kingAttributes.forEach {
                val imh = it.itemMainHand()
                if (imh.b > itemMainHand.b) {
                    itemMainHand = imh
                }
                val a = it.armor()
                if (a.b > armor.b) {
                    armor = a
                }
            }
            if (itemMainHand.a != null) {
                entity!!.itemInMainHand = itemMainHand.a!!.clone()
            }
            if (armor.a != null) {
                entity!!.itemInHead = armor.a!!.helmet?.clone()
                entity!!.itemInChest = armor.a!!.chestplate?.clone()
                entity!!.itemInLegs = armor.a!!.leggings?.clone()
                entity!!.itemInFeet = armor.a!!.boots?.clone()
            }
            markToRender()
        }
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

    override fun registerRenderListener(listener: Consumer<RendableEntity>) {
        this.listenerToRender = listener
    }

    override fun unregisterRenderListener() {
        this.listenerToRender = null
    }

    override fun markToRender(){ // called to render entity to everyone
        if(isSpawned()) {
            this.listenerToRender?.accept(this)
        }
    }

    override fun renderTo(player: Player) {
        entity?.renderToPlayer(player)
    }
}