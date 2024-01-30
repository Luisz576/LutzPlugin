package com.luisz.lutz.database

import com.luisz.lutz.profile.LutzProfile
import org.bukkit.entity.Player

interface ILutzDatabase {
    fun loadProfile(player: Player): LutzProfile?
    fun saveProfile(profile: LutzProfile)
    fun save()
}