package com.luisz.lutz.game.manager.scoreboard

import com.luisz.lutz.game.profile.GamePlayerProfile

interface IScoreRender {
    fun render(data: ScoreboardData, profile: GamePlayerProfile)
    fun clearRenders()
}