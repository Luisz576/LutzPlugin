package com.luisz.lutz.game.shop.money

import com.luisz.lutz.util.ItemUtils
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

data class Money(var iron: Int, var gold: Int, var diamond: Int, var emerald: Int){
    companion object{
        fun getInvMoney(inv: Inventory): Money{
            var iron = 0
            var gold = 0
            var diamond = 0
            var emerald = 0
            for(i in 0..inv.size){
                val item = inv.getItem(i)
                if(item != null){
                    when(item.type){
                        Material.IRON_INGOT -> iron += item.amount
                        Material.GOLD_INGOT -> gold += item.amount
                        Material.DIAMOND -> diamond += item.amount
                        Material.EMERALD -> emerald += item.amount
                        else -> {}
                    }
                }
            }
            return Money(iron, gold, diamond, emerald)
        }
        fun free(): Money{
            return Money(0, 0, 0, 0)
        }
    }
    fun isFree(): Boolean{
        return iron == 0 && gold == 0 && diamond == 0 && emerald == 0
    }

    fun isEnought(m: Money): Boolean{
        return m.iron >= iron && m.gold >= gold && m.diamond > diamond && m.emerald > emerald
    }

    fun reduceFrom(material: Material, amount: Int = 1){
        when(material){
            Material.IRON_INGOT -> iron -= amount
            Material.GOLD_INGOT -> gold -= amount
            Material.DIAMOND -> diamond -= amount
            Material.EMERALD -> emerald -= amount
            else -> {}
        }
    }

    fun toItems(): MutableList<ItemStack> {
        val items = ArrayList<ItemStack>()
        ItemUtils.addItemsOfSameTypeToList(items, Material.IRON_INGOT, iron)
        ItemUtils.addItemsOfSameTypeToList(items, Material.GOLD_INGOT, gold)
        ItemUtils.addItemsOfSameTypeToList(items, Material.DIAMOND, diamond)
        ItemUtils.addItemsOfSameTypeToList(items, Material.EMERALD, emerald)
        return items
    }
}