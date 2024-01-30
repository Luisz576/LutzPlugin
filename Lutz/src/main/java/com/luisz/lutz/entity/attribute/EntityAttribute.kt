package com.luisz.lutz.entity.attribute

import com.luisz.lapi.common.tuple.Tuple
import com.luisz.lutz.util.ArmorSet
import org.bukkit.inventory.ItemStack

abstract class EntityAttribute {
    open fun armor(): Tuple<ArmorSet?, Int> {
        return Tuple(null, 0)
    }
    open fun itemMainHand(): Tuple<ItemStack?, Int> {
        return Tuple(null, 0)
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
    open fun death(){}
}