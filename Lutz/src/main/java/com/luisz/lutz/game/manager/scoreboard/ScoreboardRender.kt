package com.luisz.lutz.game.manager.scoreboard

import com.luisz.lapi.common.tuple.Tuple
import com.luisz.lutz.game.ILutzGame
import com.luisz.lutz.game.profile.GamePlayerProfile
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard

class ScoreboardRender(val game: ILutzGame) : IScoreRender {

    fun getScoreboardOrRegisterNew(profile: GamePlayerProfile): Tuple<Scoreboard, Objective>{
        TODO()
    }

    private fun updateScoreboard(scoreboard: Tuple<Scoreboard, Objective>, data: ScoreboardData, profile: GamePlayerProfile) {

    }

    override fun render(data: ScoreboardData, profile: GamePlayerProfile) {
        val scoreboard: Tuple<Scoreboard, Objective> = getScoreboardOrRegisterNew(profile)

        updateScoreboard(scoreboard, data, profile)

        profile.player.scoreboard = scoreboard.a
    }
}