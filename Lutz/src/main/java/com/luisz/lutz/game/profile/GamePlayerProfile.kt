package com.luisz.lutz.game.profile

import com.luisz.lapi.common.language.Language
import com.luisz.lutz.events.player.PlayerDisconnectGameEvent
import com.luisz.lutz.events.player.PlayerReconnectGameEvent
import com.luisz.lutz.game.ILutzGame
import com.luisz.lutz.game.manager.PlayerInventoryManager
import com.luisz.lutz.game.profile.reason.PlayerDeathReason
import com.luisz.lutz.game.profile.reason.PlayerDieReason
import com.luisz.lutz.game.profile.role.Role
import com.luisz.lutz.game.team.Team
import com.luisz.lutz.message.Message
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

class GamePlayerProfile(val game: ILutzGame, val player: Player, val lang: Language = Language.EN) {
    private var role: Role = Role.SPECTATOR
    private fun setRole(role: Role){
        this.role = role
    }
    fun role(): Role{
        return this.role
    }

    private var playerState = PlayerState.NONE
    private fun setState(state: PlayerState){
        this.playerState = state
    }
    fun state(): PlayerState{
        return playerState
    }

    private var killer: Entity? = null
    private fun setKiller(killer: Entity?){
        this.killer = killer
    }
    fun killer(): Entity?{
        return killer
    }

    private var team: Team? = null
    private fun setTeam(team: Team?){
        this.team?.quit(this)
        this.team = team
    }
    fun getTeam(): Team?{
        return team
    }

    private val inventoryManager = PlayerInventoryManager(this)

    fun makeAPlayerOf(team: Team) {
        setTeam(team)
        if(role == Role.PLAYER){
            return
        }
        setRole(Role.PLAYER)
        setState(PlayerState.ALIVE)
    }

    fun makeASpectator() {
        setTeam(null)
        if(role == Role.SPECTATOR){
            return
        }
        setRole(Role.SPECTATOR)
        setState(PlayerState.NONE)
    }

    fun playerRemovedFromTeam() {
        setTeam(null)
        setRole(Role.SPECTATOR)
        setState(PlayerState.NONE)
    }

    val statistics = GameProfileStatistics()

    fun killPlayer(reason: PlayerDeathReason, killer: Entity?){
        if(this.playerState == PlayerState.ALIVE){
            setKiller(killer)
            if(!hasKing()){
                eliminatePlayer(reason)
                return
            }
            doDie(false, reason)
        }
    }

    private fun eliminatePlayer(reason: PlayerDeathReason){
        if(playerState == PlayerState.ELIMINATED){
            return
        }
        setState(PlayerState.ELIMINATED)
        doDie(true, reason)
    }
    private fun doDie(finalKill: Boolean, reason: PlayerDeathReason){
        if(reason.canTeleportAfterThis) {
            player.teleport(game.soulsSpawn())
        }
        inventoryManager.clear()
        val reasonToEvent = when(reason){
            PlayerDeathReason.ENEMY -> {
                PlayerDieReason.enemyReason(finalKill, this, killer())
            }
            PlayerDeathReason.UNKNOWN -> {
                PlayerDieReason.unknownReason(finalKill, this, killer())
            }
            PlayerDeathReason.DISCONNECT -> {
                PlayerDieReason.disconnectReason(finalKill, this)
            }
            PlayerDeathReason.VOID -> {
                PlayerDieReason.voidReason(finalKill, this)
            }
        }
        Bukkit.getPluginManager().callEvent(reasonToEvent.createEvent(game))
    }

    fun respawn(){
        if(hasKing()){
            setState(PlayerState.ALIVE)
            doRespawn()
        }
    }
    private fun doRespawn(){
        if(team != null){
            player.teleport(team!!.kingdom.playerSpawn())
            inventoryManager.respawn()
        }
    }

    fun hasKing(): Boolean {
        return role == Role.PLAYER && team != null && team!!.hasKing()
    }

    fun disconnectPlayer(){
        // TODO: verificar quando matarem o rei se tem alguem desconectado
        if(playerState == PlayerState.DISCONNECTED){
            return
        }
        setState(PlayerState.DISCONNECTED)
        Bukkit.getPluginManager().callEvent(PlayerDisconnectGameEvent(game, this))
        if(!hasKing()){
            eliminatePlayer(PlayerDeathReason.DISCONNECT)
        }
    }

    fun reconnectPlayer(){
        if(playerState != PlayerState.DISCONNECTED){
            return
        }
        setState(PlayerState.DIED)
        Bukkit.getPluginManager().callEvent(PlayerReconnectGameEvent(game, this))
    }

    fun sendMessage(message: Message, vararg vars: String) {
        player.sendMessage(message.get("", lang, *vars))
    }
}