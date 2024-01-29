package com.luisz.lutz.entity.attribute

import com.luisz.lutz.util.ArmorSet
import org.bukkit.inventory.ItemStack

abstract class EntityAttribute {
    open fun armor(): ArmorSet? {
        return null
    }
    open fun itemMainHand(): ItemStack? {
        return null
    }
    open fun secondSelfRegenerate(): Int{
        return 0
    }
    fun itSelfRegenerates(): Boolean{
        return secondSelfRegenerate() > 0
    }
    open fun secondRegenerateNextTeamMembers(): Int{
        return 0
    }
    fun itRegeneratesNextTeamMembers(): Boolean{
        return secondRegenerateNextTeamMembers() > 0
    }
    // TODO: open fun extraMoney()
    open fun death(){}
}