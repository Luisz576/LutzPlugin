package com.luisz.lutz.message

enum class MessageKeys(val messageKey: String) {
    PLAYER_JOIN_IN_GAME_LIKE_PLAYER("player_join_in_game_like_player"),
    PLAYER_JOIN_IN_GAME_LIKE_SPECTATOR("player_join_in_game_like_spectator");

    companion object {
        private val registeredValues = ArrayList<String>()

        init{
            for(mk in entries){
                registeredValues.add(mk.messageKey)
            }
        }

        fun isValid(value: String): Boolean{
            return registeredValues.contains(value)
        }
    }
}