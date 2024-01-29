package com.luisz.lutz.game.manager.scoreboard

import com.luisz.lutz.game.ILutzGame

data class ScoreboardData(val time: Int) {
    companion object {
        fun build(game: ILutzGame): ScoreboardData {
            return ScoreboardData(game.timeInSeconds())
        }
    }
}