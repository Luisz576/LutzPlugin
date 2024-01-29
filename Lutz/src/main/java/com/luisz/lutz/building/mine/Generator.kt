package com.luisz.lutz.building.mine

import com.luisz.lutz.building.IBuilding
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Item
import org.bukkit.inventory.ItemStack
import java.util.function.Supplier

open class Generator(properties: Properties) : IBuilding {
    private val maxLevel = properties.maxLevel()
    private val baseTimeToGenerate = properties.baseTimeToGenerate()
    private val itemToGenerate = properties.itemToGenerate()
    private val timeReduceByLevel = properties.timeReduceByLevel()
    private val baseDisplayName = properties.baseDisplayName()

    private var level = 0
    override fun level(): Int {
        return level
    }
    override fun hasNextLevel(): Boolean {
        return !isDestroyed() && level < maxLevel
    }

    private var destroyed = false
    override fun doDestroyed() {
        this.destroyed = true
        // TODO: animation? changeBlocks?
    }

    override fun isDestroyed(): Boolean {
        return destroyed
    }

    private var location: Location? = null
    fun getLocation(): Location?{
        return location
    }
    fun setLocation(l: Location){
        this.location = l
    }

    private var time = 0
    fun time(): Int{
        return time
    }

    override fun reset() {
        TODO("Not yet implemented")
    }

    private fun canGenerate(): Boolean{
        return !isDestroyed() && location != null
    }

    private fun getTimeToGenerate(): Int{
        return baseTimeToGenerate - (timeReduceByLevel * level)
    }

    override fun upgrade() {
        if(!isDestroyed()){
            level++
            // TODO: animation? change blocks?
        }
    }

    override fun updateSecond() {
        if(canGenerate()){
            if(time <= 0){
                time = getTimeToGenerate()
                generate()
            }
            time--
        }
        updateDisplay()
    }

    fun updateDisplay(){
        if(hasDisplayName()){
            // TODO:
        }
    }

    fun hasDisplayName(): Boolean{
        return baseDisplayName.isNotEmpty()
    }

    private fun generate(){
        if(itemToGenerate != null){
            val items = itemToGenerate.get()
            if(location != null) {
                for (item in items) {
                    val entity = location!!.world?.spawnEntity(location!!, EntityType.DROPPED_ITEM)
                    if(entity is Item){
                        entity.itemStack = item
                    }
                }
            }
        }
    }

    fun copyProperties(): Properties{
        return Properties()
            .maxLevel(maxLevel)
            .itemToGenerate(itemToGenerate)
            .baseTimeToGenerate(baseTimeToGenerate)
            .baseTimeToGenerate(baseTimeToGenerate)
            .baseDisplayName(baseDisplayName)
            .timeReduceByLevel(timeReduceByLevel)
    }

    companion object {
        class Properties{
            private var maxLevel = 0
            fun maxLevel(maxLevel: Int): Properties{
                this.maxLevel = maxLevel
                return this
            }
            fun maxLevel(): Int{
                return maxLevel
            }

            private var baseTimeToGenerate = 0
            fun baseTimeToGenerate(baseTimeToGenerate: Int): Properties{
                this.baseTimeToGenerate = baseTimeToGenerate
                return this
            }
            fun baseTimeToGenerate(): Int{
                return baseTimeToGenerate
            }

            private var timeReduceByLevel = 0
            fun timeReduceByLevel(timeReduceByLevel: Int): Properties{
                this.timeReduceByLevel = timeReduceByLevel
                return this
            }
            fun timeReduceByLevel(): Int{
                return timeReduceByLevel
            }

            private var baseDisplayName = ""
            fun baseDisplayName(baseDisplayName: String): Properties{
                this.baseDisplayName = baseDisplayName
                return this
            }
            fun baseDisplayName(): String{
                return baseDisplayName
            }

            private var itemToGenerate: Supplier<List<ItemStack>>? = null
            fun itemToGenerate(itemToGenerate: Supplier<List<ItemStack>>?): Properties{
                this.itemToGenerate = itemToGenerate
                return this
            }
            fun itemToGenerate(): Supplier<List<ItemStack>>?{
                return itemToGenerate
            }
        }
    }
}