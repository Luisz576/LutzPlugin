package com.luisz.lutz.entity

import org.bukkit.entity.Player
import java.util.function.Consumer

interface RendableEntity {
    fun registerRenderListener(listener: Consumer<RendableEntity>)
    fun unregisterRenderListener()
    fun markToRender()
    fun renderTo(player: Player)
}