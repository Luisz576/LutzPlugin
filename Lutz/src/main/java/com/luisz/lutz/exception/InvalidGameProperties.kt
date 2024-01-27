package com.luisz.lutz.exception

import com.luisz.lutz.Lutz

class InvalidGameProperties(private val errors: List<String>) : Exception(){
    fun errors(): List<String>{
        return ArrayList(errors)
    }

    fun warning(){
        val plugin = Lutz.getInstance()
        for(e in errors){
            plugin.logger.warning(e)
        }
    }

    fun info(){
        val plugin = Lutz.getInstance()
        for(e in errors){
            plugin.logger.info(e)
        }
    }
}