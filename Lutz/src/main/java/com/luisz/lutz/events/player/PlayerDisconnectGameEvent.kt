package com.luisz.lutz.events.player

import com.luisz.lutz.events.GameEvent
import com.luisz.lutz.game.ILutzGame
import com.luisz.lutz.game.profile.GamePlayerProfile

class PlayerDisconnectGameEvent(game: ILutzGame, val profile: GamePlayerProfile) : GameEvent(game)