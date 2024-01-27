package com.luisz.lutz.message

import com.luisz.lapi.common.language.Language

class Message {
    private val messages = HashMap<Language, String>()

    fun register(key: Language, message: String){
        messages[key] = message
    }

    fun get(default: String = "", key: Language, vararg vars: String): String {
        var message = messages[key]
        for(v in vars){
            if(message != null) {
                message = message.replaceFirst(LutzMessages.MESSAGE_VAR, v)
                continue
            }
            break
        }
        if(message != null){
            return message
        }
        return default
    }
}