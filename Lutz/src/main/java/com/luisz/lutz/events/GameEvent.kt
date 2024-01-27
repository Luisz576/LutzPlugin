package com.luisz.lutz.events

import com.luisz.lapi.event.IEvent
import com.luisz.lutz.game.ILutzGame

abstract class GameEvent(val game: ILutzGame) : IEvent()