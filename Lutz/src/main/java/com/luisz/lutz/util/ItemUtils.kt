package com.luisz.lutz.util

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object ItemUtils {
    fun createItem(material: Material, amount: Int = 1, name: String? = null): ItemStack{
        val item = ItemStack(material, amount)
        if(name != null){
            val itemMeta = item.itemMeta
            itemMeta!!.setDisplayName(name)
            item.setItemMeta(itemMeta)
        }
        return item
    }

    fun addItemsOfSameTypeToList(list: MutableList<ItemStack>, m: Material, amount: Int){
        var v = amount
        while(v > 0){
            val n = v.coerceAtMost(64)
            list.add(ItemUtils.createItem(Material.IRON_INGOT, n))
            v -= n
        }
    }
}