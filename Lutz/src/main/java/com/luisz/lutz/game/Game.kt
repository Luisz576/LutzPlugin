package com.luisz.lutz.game

import com.luisz.lutz.Lutz
import com.luisz.lutz.entity.RendableEntity
import com.luisz.lutz.game.manager.SoulsManager
import com.luisz.lutz.game.manager.rendableentities.RendableEntitiesManager
import com.luisz.lutz.game.manager.scoreboard.ScoreboardRender
import com.luisz.lutz.game.team.TeamsManager
import com.luisz.lutz.game.properties.GameProperties
import com.luisz.lutz.message.Message
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList

class Game(properties: GameProperties) : ILutzGame(properties) {
    private val recruitingTime = properties.recruitingTime()
    private val stoppingTime = properties.stoppingTime()

    private val teamsManager = TeamsManager(this)
    override fun amountOfPlayers(): Int {
        return teamsManager.amountOfPlayers()
    }
    override fun isPlayer(player: Player?): Boolean {
        if(player != null){
            return teamsManager.isPlayer(player)
        }
        return false
    }

    private val scoreboardRender = ScoreboardRender(this)

    private val soulsManager = SoulsManager(this, properties.timeToRespawn())

    private val rendableEntitiesManager: RendableEntitiesManager
    override fun registerRendableEntity(rendableEntity: RendableEntity) {
        rendableEntitiesManager.registerRendableEntity(rendableEntity)
    }
    override fun unregisterRendableEntity(rendableEntity: RendableEntity) {
        rendableEntitiesManager.unregisterRendableEntity(rendableEntity)
    }
    override fun renderRendableEntitiesTo(player: Player){
        rendableEntitiesManager.renderRendableEntitiesTo(player)
    }

    private val gameListener = GameListener(this, teamsManager::onKingDie)

    private var timerId: Int = -1
    private var timeInSeconds: Int = 0
    override fun timeInSeconds(): Int {
        return timeInSeconds
    }

    init {
        teamsManager.buildTeamsFromData(properties.arena().teams)
        rendableEntitiesManager = RendableEntitiesManager(teamsManager, soulsManager)
        Bukkit.getPluginManager().registerEvents(gameListener, Lutz.getInstance())
        timerId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Lutz.getInstance(), this::updateSecond, 20, 0)
    }

    private fun updateSecond() {
        when (state()) {
            GameState.RECRUITING -> {
                if (amountOfPlayers() < minPlayers()) {
                    timeInSeconds = recruitingTime
                }
                if (timeInSeconds <= 0) {
                    startGame()
                }
            }
            GameState.RUNNING -> {
                soulsManager.updateSecond()
                teamsManager.renderScore(scoreboardRender)
            }
            GameState.STOPPING -> {
                if(timeInSeconds <= 0){
                    closeGame()
                }
            }
            else -> return
        }
        --timeInSeconds
    }

    override fun quit(player: Player?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onClose() {
        if (state() == GameState.NONE || state() == GameState.CLOSING) {
            return
        }
        setState(GameState.CLOSING)

        soulsManager.free()
        scoreboardRender.clearRenders()
        rendableEntitiesManager.unregisterAll()
        teamsManager.resetKingdoms()

        HandlerList.unregisterAll(gameListener)
        Bukkit.getScheduler().cancelTask(timerId)

        setState(GameState.NONE)
    }

    // states
    private fun startRecruitment() {
        setState(GameState.RECRUITING)
        timeInSeconds = recruitingTime
        // TODO:
    }

    private fun startGame() {
        setState(GameState.RUNNING)
        // TODO:
    }

    private fun stopGame(reason: GameEndsReason) {
        setState(GameState.STOPPING)
        timeInSeconds = stoppingTime
        // TODO:
    }

    override fun broadcastMessage(message: Message, vararg vars: String) {
        teamsManager.forEachTeam {
            it.forEachMember { member ->
                member.sendMessage(message, *vars)
            }
        }
    }
}