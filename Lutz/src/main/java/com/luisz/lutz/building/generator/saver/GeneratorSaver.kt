package com.luisz.lutz.building.generator.saver

import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.saver.ConfigSaver
import com.luisz.lutz.building.generator.Generator

class GeneratorSaver : ConfigSaver<Generator> {
    override fun save(config: LConfig, key: String, value: Generator): Boolean {
        val properties = value.copyProperties()
        config.setValue("$key.maxLevel", properties.maxLevel())
        config.setValue("$key.baseTimeToGenerate", properties.baseTimeToGenerate())
        config.setValue("$key.baseDisplayName", properties.baseDisplayName())
        config.setValue("$key.timeReduceByLevel", properties.timeReduceByLevel())
        val s = properties.itemsToGenerate()
        if(s != null){
            val items = s.items()
            if(items.isNotEmpty()){
                config.setValue("$key.itemToGenerate", items.map {
                    it.type.name
                }.toList())
            }
        }
        return true
    }
}