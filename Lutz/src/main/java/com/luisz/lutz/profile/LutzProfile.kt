package com.luisz.lutz.profile

import com.luisz.lutz.database.ILutzDatabase
import org.bukkit.entity.Player

data class LutzProfile(private val db: ILutzDatabase, val player: Player, private var wins: Int) {
    fun wins(): Int{
        return wins
    }
    fun addWin(){
        wins++
    }

    fun save(): LutzProfile{
        db.saveProfile(this)
        return this
    }

    companion object {
        fun createNew(db: ILutzDatabase, player: Player): LutzProfile{
            return LutzProfile(db, player, 0)
        }
    }
}