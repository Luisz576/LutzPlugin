package com.luisz.lutz

import com.luisz.lutz.arena.manager.LutzArenas
import com.luisz.lutz.database.ILutzDatabase
import com.luisz.lutz.database.YMLDatabase
import org.bukkit.plugin.java.JavaPlugin

class Lutz : JavaPlugin() {
    companion object {
        private var instance: Lutz? = null
        fun getInstance(): Lutz{
            if(instance != null){
                return instance as Lutz
            }
            throw RuntimeException("Lutz not initialized!")
        }

        private var db: ILutzDatabase? = null
        fun getDb(): ILutzDatabase{
            if(db != null){
                return db as ILutzDatabase
            }
            throw RuntimeException("No Database initialized!")
        }

        private var arenasManager: LutzArenas? = null
        fun getArenasManager(): LutzArenas{
            if(arenasManager != null){
                return arenasManager as LutzArenas
            }
            throw RuntimeException("Arenas Manager initialized!")
        }
    }

    override fun onEnable() {
        instance = this
        db = YMLDatabase(this, "db")

        arenasManager = LutzArenas(this, "arenas")

        logger.info("Enabled!")
    }

    override fun onDisable() {
        logger.info("Disabled!")
    }
}