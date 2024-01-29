package com.luisz.lutz.building.mine.loader

import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.loader.ConfigLoader
import com.luisz.lutz.building.mine.GeneratedItems
import com.luisz.lutz.building.mine.Generator
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class GeneratorLoader : ConfigLoader<Generator?> {
    override fun load(config: LConfig, key: String): Generator? {
        val maxLevel = config.getInt("$key.maxLevel")
        val baseTimeToGenerate = config.getInt("$key.baseTimeToGenerate")
        val baseDisplayName = config.getString("$key.baseDisplayName")
        val timeReduceByLevel = config.getInt("$key.timeReduceByLevel")

        if(maxLevel < 0 || baseTimeToGenerate < 0 || baseDisplayName == null || timeReduceByLevel < 0){
            return null
        }

        val materials = config.getList("$key.itemToGenerate") as MutableList<String>?
        var itemsToGenerate: GeneratedItems? = null
        if(materials != null && materials.size > 0){
            val items = ArrayList<ItemStack>()
            for(m in materials){
                items.add(ItemStack(Material.valueOf(m)))
            }
            itemsToGenerate = GeneratedItems(items)
        }

        return Generator(Generator.Companion.Properties()
            .maxLevel(maxLevel)
            .baseTimeToGenerate(baseTimeToGenerate)
            .baseDisplayName(baseDisplayName)
            .timeReduceByLevel(timeReduceByLevel)
            .itemsToGenerate(itemsToGenerate)
        )
    }
}