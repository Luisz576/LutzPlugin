package com.luisz.lutz.arena.saver

import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.saver.ConfigSaver
import com.luisz.lutz.arena.Arena
import com.luisz.lutz.game.kingdom.saver.KingdomSaver
import com.luisz.lutz.game.team.data.saver.TeamDataSaver

class ArenaSaver : ConfigSaver<Arena> {
    private val kingdomSaver = KingdomSaver()
    private val teamDataSaver = TeamDataSaver()

    override fun save(config: LConfig, key: String, value: Arena): Boolean {
        TODO("Not yet implemented")
    }
}