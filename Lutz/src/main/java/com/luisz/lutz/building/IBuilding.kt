package com.luisz.lutz.building

import com.luisz.lutz.util.SecondUpdater

interface IBuilding : SecondUpdater {
    fun reset()
    fun doDestroyed()
    fun isDestroyed(): Boolean
    fun level(): Int
    fun hasNextLevel(): Boolean
    fun upgrade()
}