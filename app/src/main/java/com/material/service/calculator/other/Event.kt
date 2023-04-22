package com.material.service.calculator.other

open class Event<out T>(private val content:T) {

    var hasBeenEnabled = false
        private set



    fun getContentIfNotHandled():T?{
        return if(hasBeenEnabled){
            null
        }else{
            hasBeenEnabled=true
            content
        }
    }

    fun peekContent():T = content

}