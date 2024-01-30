package com.luisz.lutz.building.generator

import org.bukkit.inventory.ItemStack

data class GeneratedItems(private val values: List<ItemStack>) {
    fun items(): List<ItemStack>{
        return ArrayList(values)
    }
}