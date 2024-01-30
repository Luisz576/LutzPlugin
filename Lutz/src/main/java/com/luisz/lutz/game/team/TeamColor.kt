package com.luisz.lutz.game.team

import org.bukkit.ChatColor
import org.bukkit.Material

enum class TeamColor(val color: ChatColor, val glassPane: Material) {
    RED(ChatColor.RED, Material.RED_STAINED_GLASS_PANE),
    BLUE(ChatColor.BLUE, Material.BLUE_STAINED_GLASS_PANE),
    GREEN(ChatColor.GREEN, Material.GREEN_STAINED_GLASS_PANE),
    YELLOW(ChatColor.YELLOW, Material.YELLOW_STAINED_GLASS_PANE)
}