package com.luisz.lutz.game.profile.reason

enum class PlayerDeathReason(val canTeleportAfterThis: Boolean) {
    UNKNOWN(true),
    DISCONNECT(false),
    VOID(true),
    ENEMY(true);
}