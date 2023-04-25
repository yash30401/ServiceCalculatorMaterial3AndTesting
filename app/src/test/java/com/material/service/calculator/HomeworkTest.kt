package com.material.service.calculator


import com.google.common.truth.Truth.assertThat
import com.material.service.calculator.Testing.Homework
import org.junit.Test

class HomeworkTest{

    @Test
    fun `string has 0 braces returns true`(){
        val result = Homework.chechBraces(
            "Hello"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `string has opening brace but not has closing brace return false`(){
        val result = Homework.chechBraces(
            "(Hello"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `string has closing brace but not has opening brace return false`(){
        val result = Homework.chechBraces(
            "Hello)"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty string returns true`(){
        var result = Homework.isPalidrome("")

        assertThat(result).isTrue()
    }

    @Test
    fun `unmatched string return false`(){
        var result = Homework.isPalidrome(
            "Hello"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `matched string return true`(){
        var result  = Homework.isPalidrome(
            "nitin"
        )

        assertThat(result).isTrue()
    }


    @Test
    fun `empty email return false`(){
        val result = Homework.emailValidation("")

        assertThat(result).isFalse()
    }

    @Test
    fun `email does not contain @gmail return false`(){
        val result = Homework.emailValidation("yashveersinghgamil.com")

        assertThat(result).isFalse()
    }

    @Test
    fun `valid email return true`(){
        val result = Homework.emailValidation("yashveersingh30401@gmail.com")

        assertThat(result).isTrue()
    }

}