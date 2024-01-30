package com.luisz.lutz.arena.saver

import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.saver.ConfigSaver
import com.luisz.lutz.arena.Arena
import com.luisz.lutz.game.team.data.TeamData

class ArenaSaver(private val teamDataSaver: ConfigSaver<TeamData>) : ConfigSaver<Arena> {
    override fun save(config: LConfig, key: String, value: Arena): Boolean {
        TODO("Not yet implemented")
    }
}