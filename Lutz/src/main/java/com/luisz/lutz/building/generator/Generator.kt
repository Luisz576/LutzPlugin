package com.luisz.lutz.building.generator

import com.luisz.lapi.entity.hologram.Hologram
import com.luisz.lutz.building.IBuilding
import com.luisz.lutz.util.RomanNumeral
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Item

open class Generator(properties: Properties) : IBuilding {
    private val maxLevel = properties.maxLevel()
    private val baseTimeToGenerate = properties.baseTimeToGenerate()
    private val itemsToGenerate = properties.itemsToGenerate()
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

    private val hologram = Hologram()

    init {
        updateDisplay()
    }

    override fun reset() {
        TODO("Not yet implemented")
        updateDisplay()
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
            updateDisplay()
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
    }

    private fun renderHologram(title: String) {
        val l = getHologramLocation();
        if(l != null) {
            hologram.setTitle(title)
            if(hologram.isSpawned){
                hologram.teleport(l);
            }else{
                hologram.spawn(l);
            }
        }
    }

    private fun getHologramLocation(): Location?{
        if(location != null) {
            val l = location!!.clone();
            l.add(0.0, 1.0, 0.0);
            return l;
        }
        return null;
    }

    fun updateDisplay(){
        if(hasDisplayName()){
            renderHologram("$baseDisplayName - ${RomanNumeral.fromNumber(level)!!.text}");
        }
    }

    fun hasDisplayName(): Boolean{
        return baseDisplayName.isNotEmpty()
    }

    private fun generate(){
        if(itemsToGenerate != null){
            val items = itemsToGenerate.items()
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
            .itemsToGenerate(itemsToGenerate)
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

            private var itemsToGenerate: GeneratedItems? = null
            fun itemsToGenerate(itemsToGenerate: GeneratedItems?): Properties{
                this.itemsToGenerate = itemsToGenerate
                return this
            }
            fun itemsToGenerate(): GeneratedItems?{
                return itemsToGenerate
            }
        }
    }
}