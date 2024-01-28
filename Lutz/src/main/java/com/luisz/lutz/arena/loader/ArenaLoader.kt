package com.luisz.lutz.arena.loader

import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.adapter.LoaderAdapter
import com.luisz.lutz.arena.Arena
import com.luisz.lutz.game.kingdom.loader.KingdomLoader
import com.luisz.lutz.game.team.data.loader.TeamDataLoader

class ArenaLoader : LoaderAdapter<Arena> {
    private val kingdomLoader = KingdomLoader()
    private val teamDataLoader = TeamDataLoader()

    override fun load(config: LConfig?, key: String?): Arena {
        TODO("Not yet implemented")
    }
}