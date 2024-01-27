package com.luisz.lutz.game

enum class GameState(val isOpenState: Boolean) {
    STARTING(false),
    RECRUITING(true),
    RUNNING(true),
    STOPPING(false),
    CLOSING(false),
    NONE(false)
}