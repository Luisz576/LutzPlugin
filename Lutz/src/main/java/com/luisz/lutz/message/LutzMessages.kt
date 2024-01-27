package com.luisz.lutz.message

import com.luisz.lapi.config.LConfig
import com.luisz.lutz.Lutz
import com.luisz.lutz.message.loader.DefaultMessagesLoader
import com.luisz.lutz.message.saver.DefaultPluginMessagesSaver

object LutzMessages {
    private val config = LConfig("messages", Lutz.getInstance())
    private val messages: MutableMap<MessageKeys, Message>

    private const val MESSAGES_KEY = "messages"

    const val MESSAGE_VAR = "%var%"

    fun getMessage(key: MessageKeys): Message? {
        return messages[key]
    }

    init{
        messages = DefaultMessagesLoader().load(config, MESSAGES_KEY)
        if(messages.isEmpty()){
            DefaultPluginMessagesSaver().save(config, MESSAGES_KEY, true)
            messages.putAll(DefaultMessagesLoader().load(config, MESSAGES_KEY))
        }
    }
}