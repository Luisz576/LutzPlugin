package com.luisz.lutz.game.manager.rendableentities

import com.luisz.lutz.entity.RendableEntity
import org.bukkit.entity.Player

interface IRendableEntitiesManager {
    fun registerRendableEntity(rendableEntity: RendableEntity)
    fun unregisterRendableEntity(rendableEntity: RendableEntity)
    fun renderRendableEntitiesTo(player: Player)
}