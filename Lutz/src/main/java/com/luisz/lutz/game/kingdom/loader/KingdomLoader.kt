package com.luisz.lutz.game.kingdom.loader

import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.loader.ConfigLoader
import com.luisz.lutz.building.generator.Generator
import com.luisz.lutz.game.kingdom.Kingdom

class KingdomLoader(private val generatorLoader: ConfigLoader<Generator>) : ConfigLoader<Kingdom> {
    override fun load(config: LConfig, key: String): Kingdom {
        TODO("Not yet implemented")
    }
}