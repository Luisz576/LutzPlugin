package com.luisz.lutz.events.king

import com.luisz.lutz.entity.king.King
import com.luisz.lutz.events.GameEvent
import com.luisz.lutz.game.ILutzGame

class KingDieGameEvent(game: ILutzGame, val king: King) : GameEvent(game)