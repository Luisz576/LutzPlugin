package com.luisz.lutz.game.team.data.loader

import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.loader.ConfigLoader
import com.luisz.lutz.game.kingdom.Kingdom
import com.luisz.lutz.game.team.data.TeamData

class TeamDataLoader(private val kingdomLoader: ConfigLoader<Kingdom>) : ConfigLoader<TeamData> {
    override fun load(config: LConfig?, key: String?): TeamData {
        TODO("Not yet implemented")
    }
}