package com.luisz.lutz.game.team.data.saver

import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.saver.ConfigSaver
import com.luisz.lutz.game.team.data.TeamData

class TeamDataSaver : ConfigSaver<TeamData> {
    override fun save(config: LConfig, key: String, value: TeamData): Boolean {
        TODO("Not yet implemented")
    }
}