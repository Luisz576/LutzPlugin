package com.luisz.lutz.game.shop

import com.luisz.lutz.game.shop.money.Money
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

interface IShop {
    fun canBuy(buyerInv: Inventory, index: Int): Boolean
    fun buy(buyerInv: Inventory, index: Int): Boolean
    fun getItemPrice(index: Int): Money
    fun openShopTo(p: Player)
    fun closeTo(p: Player)
}