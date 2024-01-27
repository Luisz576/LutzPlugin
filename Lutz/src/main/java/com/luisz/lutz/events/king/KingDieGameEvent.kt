package com.luisz.lutz.events.king

import com.luisz.lutz.entity.King
import com.luisz.lutz.events.GameEvent
import com.luisz.lutz.game.ILutzGame

class KingDieGameEvent(game: ILutzGame, king: King) : GameEvent(game)