package com.luisz.lutz.events.player.shop

import com.luisz.lutz.events.GameEvent
import com.luisz.lutz.game.ILutzGame
import com.luisz.lutz.game.profile.GamePlayerProfile
import com.luisz.lutz.game.shop.IShop

class PlayerOpenShopGameEvent(game: ILutzGame, val shop: IShop, val profile: GamePlayerProfile) : GameEvent(game)