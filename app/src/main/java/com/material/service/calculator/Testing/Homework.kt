package com.material.service.calculator.Testing

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

    /*
    * Checking if the string is palindrome or not
    * ...empty string
    * ...unmatched string
    * ...matched string
    * ...
     */
    fun isPalidrome(str:String):Boolean{
        var str2 = str.reversed()

        return str2==str
    }
}