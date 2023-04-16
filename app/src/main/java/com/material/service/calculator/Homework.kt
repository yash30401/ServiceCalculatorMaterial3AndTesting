package com.material.service.calculator

object Homework {

    /*
    * Checking If the string has correct number of braces...
    * ...if the string has 0 braces
    * ...if the string only have closing brace
    * ...if the string only have opening brace
    * ...if the string has different number of braces
     */
    fun chechBraces(braces:String):Boolean{
        return braces.count{ it=='('} == braces.count { it==')' }
    }

}