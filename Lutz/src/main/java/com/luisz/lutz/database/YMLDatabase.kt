package com.luisz.lutz.database

import com.luisz.lapi.config.LConfig
import org.bukkit.plugin.Plugin

class YMLDatabase(plugin: Plugin, filename: String) : ILutzDatabase {
    private val config = LConfig(filename, plugin)

    override fun save() {
        config.save()
    }
}