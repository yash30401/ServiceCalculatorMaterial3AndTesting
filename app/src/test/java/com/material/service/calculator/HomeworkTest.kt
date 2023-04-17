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

}