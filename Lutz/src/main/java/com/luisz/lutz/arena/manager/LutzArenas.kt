package com.luisz.lutz.arena.manager

import com.luisz.lapi.common.manager.IManager
import com.luisz.lapi.config.LConfig
import com.luisz.lutz.arena.Arena
import com.luisz.lutz.arena.loader.ArenaLoader
import com.luisz.lutz.arena.saver.ArenaSaver
import com.luisz.lutz.building.generator.loader.GeneratorLoader
import com.luisz.lutz.building.generator.saver.GeneratorSaver
import com.luisz.lutz.game.kingdom.loader.KingdomLoader
import com.luisz.lutz.game.kingdom.saver.KingdomSaver
import com.luisz.lutz.game.team.data.loader.TeamDataLoader
import com.luisz.lutz.game.team.data.saver.TeamDataSaver
import org.bukkit.plugin.Plugin

class LutzArenas(plugin: Plugin, filename: String) : IManager<Arena, String> {
    private val config = LConfig(filename, plugin)
    private val arenaSaver = ArenaSaver(TeamDataSaver(KingdomSaver(GeneratorSaver())))
    private val arenaLoader = ArenaLoader(TeamDataLoader(KingdomLoader(GeneratorLoader())))

    private val arenasRootKey = "arenas"
    private val arenasKeysKey = "arenas_keys"

    private val arenas = HashMap<String, Arena>()

    init{
        initialize()
    }

    private fun initialize(){
        val aKeys = config.getList(arenasKeysKey) as List<String>?
        if(aKeys != null) {
            for (ak in aKeys) {
                arenaLoader.load(config, buildArenaKey(ak))
            }
        }
    }

    override fun register(arena: Arena): Boolean {
        if(!contains(arena.id)){
            if(arenaSaver.save(config, buildArenaKey(arena.id), arena)){
                save()
                arenas[arena.id] = arena
                return true
            }
        }
        return false
    }

    override fun unregister(id: String): Boolean {
        if(contains(id)){
            config.remove(buildArenaKey(id))
            save()
            arenas.remove(id)
            return true
        }
        return false
    }

    private fun buildArenaKey(id: String): String {
        return "$arenasRootKey.$id"
    }

    override fun unregisterAll(): Boolean {
        var s = true
        for(a in arenas){
            if(!unregister(a.value.id)){
                s = false
            }
        }
        return s
    }

    override fun get(id: String): Arena? {
        return arenas[id]
    }

    override fun contains(id: String): Boolean {
        return arenas.containsKey(id)
    }

    private fun save(){
        config.setValue(arenasKeysKey, ArrayList(arenas.keys))
        config.save()
    }
}