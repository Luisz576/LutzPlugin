package com.luisz.lutz.game.kingdom.saver

import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.saver.ConfigSaver
import com.luisz.lutz.game.kingdom.Kingdom

class KingdomSaver : ConfigSaver<Kingdom> {
    override fun save(config: LConfig, key: String, kingdom: Kingdom): Boolean {
        TODO("Kigdom todo")
    }
}