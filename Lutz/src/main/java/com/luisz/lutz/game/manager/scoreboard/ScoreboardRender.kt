package com.luisz.lutz.game.manager.scoreboard

import com.luisz.lapi.common.tuple.Tuple
import com.luisz.lutz.game.ILutzGame
import com.luisz.lutz.game.profile.GamePlayerProfile
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import java.util.*

class ScoreboardRender(val game: ILutzGame) : IScoreRender {
    val SCOREBOARD_TITLE: String =
        ChatColor.YELLOW.toString() + "<<" + ChatColor.RED + "Lutsz" + ChatColor.YELLOW + ">>"

    private val scoreboards =
        HashMap<GamePlayerProfile, Tuple<Scoreboard, Objective>>()

    private fun getScoreboardOrRegisterNew(profile: GamePlayerProfile): Tuple<Scoreboard, Objective>{
        var values: Tuple<Scoreboard, Objective>? = scoreboards[profile]
        if (values == null) {
            values = buildScoreboard(profile)
            scoreboards[profile] = values
        }
        return values
    }
    private fun buildScoreboard(profile: GamePlayerProfile): Tuple<Scoreboard, Objective> {
        val sm = Bukkit.getScoreboardManager()
        if(sm != null) {
            val scoreboard = sm.newScoreboard
            val objective = scoreboard.registerNewObjective("LutzPlugin", "", SCOREBOARD_TITLE)
            objective.displayName = SCOREBOARD_TITLE
            objective.displaySlot = DisplaySlot.SIDEBAR

            buildBaseScoreboardScores(scoreboard, objective, profile)

            return Tuple(scoreboard, objective)
        }
        throw RuntimeException("'ScoreboardManager' is missing!")
    }
    private fun buildBaseScoreboardScores(scoreboard: Scoreboard, objective: Objective, profile: GamePlayerProfile) {
         // TODO:
    }
    private fun buildTeamScore(scoreboard: Scoreboard, objective: Objective, teamName: String, teamKey: String, score: Int, prefix: String = "", suffix: String = ""){
        val teamRole = scoreboard.registerNewTeam(teamName)
        teamRole.addEntry(teamKey)
        if(prefix.isNotEmpty()){
            teamRole.prefix = prefix
        }
        if(suffix.isNotEmpty()){
            teamRole.suffix = suffix
        }
        objective.getScore(teamKey).score = score
    }

    private fun updateScoreboard(s: Tuple<Scoreboard, Objective>, data: ScoreboardData, profile: GamePlayerProfile) {
        // TODO:
    }

    override fun render(data: ScoreboardData, profile: GamePlayerProfile) {
        val scoreboard: Tuple<Scoreboard, Objective> = getScoreboardOrRegisterNew(profile)

        updateScoreboard(scoreboard, data, profile)

        profile.player.scoreboard = scoreboard.a
    }

    override fun clearRenders() {
        scoreboards.clear()
    }
}