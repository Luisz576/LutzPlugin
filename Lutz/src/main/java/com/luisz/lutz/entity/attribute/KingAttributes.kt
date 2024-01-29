package com.luisz.lutz.entity.attribute

import com.luisz.lapi.common.tuple.FinalTuple
import com.luisz.lapi.common.tuple.Tuple
import com.luisz.lutz.util.ArmorSet
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class KingAttributes {
    companion object {
        sealed class KingAttribute(val type: KingAttributeType) : EntityAttribute()

        private data object SelfRegeneration : KingAttribute(KingAttributeType.SELF_REGENERATION) {
            private val imh = FinalTuple(ItemStack(Material.BREAD, 1), 1)

            override fun secondSelfRegenerate(): Int {
                return 1
            }

            override fun itemMainHand(): Tuple<ItemStack?, Int> {
                return imh
            }
        }

        private data object TeamRegeneration : KingAttribute(KingAttributeType.TEAM_REGENERATION) {
            override fun secondRegenerateNextTeamMembers(): Int {
                return 1
            }
        }

        private data object ArmorLv1 : KingAttribute(KingAttributeType.ARMOR_LV1) {
            private val a = FinalTuple(ArmorSet.build(), 1)

            override fun armor(): Tuple<ArmorSet?, Int> {
                return a
            }
        }

        private data object ArmorLv2 : KingAttribute(KingAttributeType.ARMOR_LV2) {
            private val a = FinalTuple(ArmorSet.build(), 2)

            override fun armor(): Tuple<ArmorSet?, Int> {
                return a
            }
        }

        private data object ArmorLv3 : KingAttribute(KingAttributeType.ARMOR_LV3) {
            private val a = FinalTuple(ArmorSet.build(), 3)

            override fun armor(): Tuple<ArmorSet?, Int> {
                return a
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