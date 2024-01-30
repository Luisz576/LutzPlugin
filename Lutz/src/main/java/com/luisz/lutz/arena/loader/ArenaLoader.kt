package com.luisz.lutz.arena.loader

import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.loader.ConfigLoader
import com.luisz.lutz.arena.Arena
import com.luisz.lutz.game.team.data.TeamData
import com.luisz.lutz.game.team.data.loader.TeamDataLoader

class ArenaLoader(private val teamDataLoader: ConfigLoader<TeamData>) : ConfigLoader<Arena> {
    override fun load(config: LConfig, key: String): Arena {
        TODO("Not yet implemented")
    }
}