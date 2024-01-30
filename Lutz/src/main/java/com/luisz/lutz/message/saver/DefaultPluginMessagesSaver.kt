package com.luisz.lutz.message.saver

import com.luisz.lapi.common.language.Language
import com.luisz.lapi.config.LConfig
import com.luisz.lapi.config.saver.ConfigSaver
import com.luisz.lutz.message.LutzMessages
import com.luisz.lutz.message.MessageKeys
import org.bukkit.ChatColor

class DefaultPluginMessagesSaver : ConfigSaver<Boolean> {
    override fun save(config: LConfig?, key: String?, f: Boolean): Boolean {
        if(config != null && key != null){
            if(!f && config.hasKey(key)){
                return false
            }

            saveM(config, key, MessageKeys.PLAYER_JOIN_IN_GAME_LIKE_PLAYER,
                "${ChatColor.GREEN}${LutzMessages.MESSAGE_VAR} ${ChatColor.YELLOW}entrou!")
            saveM(config, key, MessageKeys.PLAYER_JOIN_IN_GAME_LIKE_SPECTATOR,
                "${ChatColor.DARK_GRAY}${LutzMessages.MESSAGE_VAR} ${ChatColor.GRAY}está espectando!")
            saveM(config, key, MessageKeys.PLAYER_RECONNECT,
                "${ChatColor.GREEN}${LutzMessages.MESSAGE_VAR} ${ChatColor.YELLOW}reconectou!")
            saveM(config, key, MessageKeys.PLAYER_DISCONNECT,
                "${ChatColor.RED}${LutzMessages.MESSAGE_VAR} ${ChatColor.YELLOW}disconectou!")
            saveM(config, key, MessageKeys.KING_IS_KILLED,
                "${ChatColor.YELLOW}O rei do time ${LutzMessages.MESSAGE_VAR} ${ChatColor.YELLOW}foi morto!")

            config.save()
        }
        return true
    }
    private fun saveM(config: LConfig, key: String, mKey: MessageKeys, value: String){
        config.setValue("$key.${mKey.messageKey}.${Language.EN}", value)
    }
}