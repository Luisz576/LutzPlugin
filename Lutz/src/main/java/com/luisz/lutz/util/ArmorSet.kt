package com.luisz.lutz.util

import org.bukkit.inventory.ItemStack

data class ArmorSet(val helmet: ItemStack?, val chestplate: ItemStack?, val leggings: ItemStack?, val boots: ItemStack?){
    companion object {
        fun build(helmet: ItemStack? = null, chestplate: ItemStack? = null, leggings: ItemStack? = null, boots: ItemStack? = null): ArmorSet{
            return ArmorSet(helmet, chestplate, leggings, boots)
        }
    }
}