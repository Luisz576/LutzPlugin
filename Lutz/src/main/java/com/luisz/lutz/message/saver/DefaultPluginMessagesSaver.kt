package com.luisz.lutz.message.saver

import com.luisz.lapi.common.language.Language
import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.adapter.SaverAdapter
import com.luisz.lutz.message.LutzMessages
import com.luisz.lutz.message.MessageKeys

class DefaultPluginMessagesSaver : SaverAdapter<Boolean> {
    override fun save(config: LConfig?, key: String?, f: Boolean) {
        if(config != null && key != null){
            if(!f && config.hasKey(key)){
                return
            }

            saveM(config, key, MessageKeys.PLAYER_JOIN_IN_GAME_LIKE_PLAYER,
                "${LutzMessages.MESSAGE_VAR} entrou!")
            saveM(config, key, MessageKeys.PLAYER_JOIN_IN_GAME_LIKE_SPECTATOR,
                "${LutzMessages.MESSAGE_VAR} está espectando!")

            config.save()
        }
    }
    private fun saveM(config: LConfig, key: String, mKey: MessageKeys, value: String){
        config.setValue("$key.${mKey.messageKey}.${Language.EN}", value)
    }
}