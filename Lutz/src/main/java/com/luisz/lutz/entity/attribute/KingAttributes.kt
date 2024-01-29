package com.luisz.lutz.entity.attribute

import com.luisz.lutz.util.ArmorSet
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class KingAttributes {
    companion object {
        sealed class KingAttribute(val type: KingAttributeType) : EntityAttribute()

        private data object SelfRegeneration : KingAttribute(KingAttributeType.SELF_REGENERATION) {
            override fun secondSelfRegenerate(): Int {
                return 1
            }

            override fun itemMainHand(): ItemStack {
                return ItemStack(Material.BREAD, 1)
            }
        }

        private data object TeamRegeneration : KingAttribute(KingAttributeType.TEAM_REGENERATION) {
            override fun secondRegenerateNextTeamMembers(): Int {
                return 1
            }
        }

        private data object ArmorLv1 : KingAttribute(KingAttributeType.ARMOR_LV1) {
            override fun armor(): ArmorSet? {
                return null
            }
        }

        private data object ArmorLv2 : KingAttribute(KingAttributeType.ARMOR_LV2) {
            override fun armor(): ArmorSet? {
                return null
            }
        }

        private data object ArmorLv3 : KingAttribute(KingAttributeType.ARMOR_LV3) {
            override fun armor(): ArmorSet? {
                return null
            }
        }

        private data object Revenge : KingAttribute(KingAttributeType.REVANGE) {
            override fun death() {
                // Todo: spawn something
            }
        }

        enum class KingAttributeType{
            SELF_REGENERATION,
            TEAM_REGENERATION,
            ARMOR_LV1,
            ARMOR_LV2,
            ARMOR_LV3,
            REVANGE
        }
    }
}