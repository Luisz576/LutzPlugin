package com.luisz.lutz

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
    }

    override fun onEnable() {
        instance = this

        logger.info("Enabled!")
    }

    override fun onDisable() {
        logger.info("Disabled!")
    }
}