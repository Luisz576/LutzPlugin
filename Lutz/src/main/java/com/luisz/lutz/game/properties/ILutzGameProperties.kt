package com.luisz.lutz.game.properties

import com.luisz.lutz.arena.Arena

interface ILutzGameProperties {
    fun arena(): Arena
    fun minPlayersByTeam(): Int
}