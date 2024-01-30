package com.luisz.lutz.game.shop

import com.luisz.lapi.player.inventory.LInventory
import com.luisz.lutz.events.player.shop.PlayerBuyShopGameEvent
import com.luisz.lutz.events.player.shop.PlayerOpenShopGameEvent
import com.luisz.lutz.game.profile.GamePlayerProfile
import com.luisz.lutz.game.shop.money.Money
import com.luisz.lutz.game.team.Team
import com.luisz.lutz.util.ItemUtils
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class KingShop(val profile: GamePlayerProfile) : IShop {
    companion object {
        const val KING_SHOP_TITLE = "§2King Shop"
        const val KING_SHOP_SIZE = 54
    }

    private val kingInventory = LInventory(KING_SHOP_TITLE, KING_SHOP_SIZE)
    private var currentPage = 0
    fun currentPage(): Int = currentPage

    private fun buildInvPage(page: Int){
        val team: Team? = profile.getTeam()
        if(team != null){
            for(i in 0..kingInventory.size){
                kingInventory.setItem(i, ItemUtils.createItem(team.color.glassPane))
            }
            when(page){
                0 -> {

                }
                1 -> {

                }
                else -> {}
            }
        }
    }

    override fun openShopTo(p: Player) {
        if(p != profile.player){
            return
        }
        buildInvPage(0)
        kingInventory.render(p)
        Bukkit.getPluginManager().callEvent(PlayerOpenShopGameEvent(profile.game, this, profile))
    }

    override fun buy(buyerInv: Inventory, index: Int): Boolean {
        if(isActionItem(index)){
            action(index)
            return false
        }
        if(canBuy(buyerInv, index)){
            val item = demandAndGet(buyerInv, index)
            buyerInv.addItem(item)
            Bukkit.getPluginManager().callEvent(PlayerBuyShopGameEvent(profile.game, this, profile, item))
            return true
        }
        return false
    }

    private fun isActionItem(index: Int): Boolean{
        // TODO:
        return false
    }

    private fun action(index: Int){
        // TODO:
    }

    private fun demandAndGet(buyerInv: Inventory, index: Int): ItemStack{
        demand(buyerInv, getItemPrice(index))
        return kingInventory.getItem(index).clone()
    }
    private fun demand(buyerInv: Inventory, price: Money){
        for(i in buyerInv.size..0){
            if(price.isFree()){
                break
            }
            val item: ItemStack? = buyerInv.getItem(i)
            if(item != null){
                price.reduceFrom(item.type, item.amount)
            }
        }
    }

    override fun getItemPrice(index: Int): Money {
        var iron = 0
        var gold = 0
        var diamond = 0
        var emerald = 0
        val item = kingInventory.getItem(index)
        if(item != null){
            when(currentPage){
                1 -> {
                    when(item.type){
                        // TODO: prices here
                        else -> {}
                    }
                }
                else -> {}
            }
        }
        return Money(iron, gold, diamond, emerald)
    }

    override fun canBuy(buyerInv: Inventory, index: Int): Boolean {
        return getItemPrice(index).isEnought(Money.getInvMoney(buyerInv))
    }

    fun isMe(name: String): Boolean {
        return KING_SHOP_TITLE == name
    }

    override fun closeTo(p: Player) {
        if(p != profile.player){
            return
        }
        currentPage = 0
    }
}