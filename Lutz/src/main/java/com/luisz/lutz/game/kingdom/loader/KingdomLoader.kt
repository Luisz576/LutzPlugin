package com.luisz.lutz.game.kingdom.loader

import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.adapter.LoaderAdapter
import com.luisz.lutz.building.mine.loader.GeneratorLoader
import com.luisz.lutz.game.kingdom.Kingdom

class KingdomLoader : LoaderAdapter<Kingdom> {
    private val generatorLoader = GeneratorLoader()

    override fun load(config: LConfig, key: String): Kingdom {
        TODO("Not yet implemented")
    }
}