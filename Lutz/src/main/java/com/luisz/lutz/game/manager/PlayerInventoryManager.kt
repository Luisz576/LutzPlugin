package com.luisz.lutz.game.manager

import com.luisz.lutz.game.profile.GamePlayerProfile
import com.luisz.lutz.game.profile.role.Role

class PlayerInventoryManager(val profile: GamePlayerProfile) {
    fun clear(){
        profile.player.inventory.clear()
    }

    fun respawn(){
        clear()
        if(profile.role() == Role.PLAYER){
            // TODO: give items
        }
    }
}