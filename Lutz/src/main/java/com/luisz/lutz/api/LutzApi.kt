package com.luisz.lutz.api

import com.luisz.lutz.Lutz
import com.luisz.lutz.arena.manager.LutzArenas
import com.luisz.lutz.profile.LutzProfile
import org.bukkit.entity.Player

class LutzApi private constructor() {
    companion object {
        private val profiles = HashMap<Player, LutzProfile>()

        val INSTANCE = LutzApi()
    }

    fun clearProfilesHash(){
        profiles.clear()
    }
    fun profile(player: Player): LutzProfile?{
        if(profiles.containsKey(player)){
            return profiles[player]
        }
        val p = Lutz.getDb().loadProfile(player)
        if(p != null){
            profiles[player] = p
        }
        return p
    }

    fun arenasManager(): LutzArenas{
        return Lutz.getArenasManager()
    }

    // TODO:
}