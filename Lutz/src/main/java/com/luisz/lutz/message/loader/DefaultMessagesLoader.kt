package com.luisz.lutz.message.loader

import com.luisz.lapi.common.language.Language
import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.loader.ConfigLoader
import com.luisz.lutz.Lutz
import com.luisz.lutz.message.Message
import com.luisz.lutz.message.MessageKeys

class DefaultMessagesLoader : ConfigLoader<MutableMap<MessageKeys, Message>> {
    override fun load(config: LConfig?, key: String?): MutableMap<MessageKeys, Message> {
        val messages = HashMap<MessageKeys, Message>()

        if(config != null && key != null) {
            for (dKey in MessageKeys.entries) {
                val newMessage = Message()
                for (lang in Language.entries) {
                    val currentKey = buildKey(key, dKey, lang)
                    if (config.hasKey(currentKey)) {
                        val message = config.getString(currentKey)
                        if (message != null) {
                            newMessage.register(lang, message)
                        } else {
                            Lutz.getInstance().logger.warning("Invalid key '$currentKey'!")
                        }
                    }
                }
                messages[dKey] = newMessage
            }
        }

        return messages
    }

    private fun buildKey(key: String, dKey: MessageKeys, lang: Language): String {
        return "$key.${dKey.messageKey}.${lang.acronym}"
    }
}