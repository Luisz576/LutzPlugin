package com.luisz.lutz.database

import com.luisz.lapi.config.LConfig
import com.luisz.lutz.profile.LutzProfile
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class YMLDatabase(plugin: Plugin, filename: String) : ILutzDatabase {
    private val profilesRootKey = "profiles"

    private val config = LConfig(filename, plugin)

    override fun loadProfile(player: Player): LutzProfile? {
        val pKey = "$profilesRootKey.${player.uniqueId}"
        if(config.hasKey(pKey)){
            val wins = config.getInt("$pKey.wins")
            if(wins < 0){
                return null
            }
            return LutzProfile(this, player, wins)
        }
        return LutzProfile.createNew(this, player)
            .save()
    }

    override fun saveProfile(profile: LutzProfile) {
        val pKey = "$profilesRootKey.${profile.player.uniqueId}"
        config.setValue("$pKey.wins", profile.wins())
        save()
    }

    override fun save() {
        config.save()
    }
}