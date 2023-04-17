package com.material.service.calculator


import com.google.common.truth.Truth.assertThat
import com.material.service.calculator.Testing.RegistrationUtil
import org.junit.Test

class RegistrationUtilTest{

    @Test
    fun `empty username return false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "Hello",
            "Hello"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated passwords true`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "Hello343",
            "Hello343"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `Username already exists return false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "Hello",
            "Hello"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password repeated incorrectly returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "Hello1",
            "Hello"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password contains less than 2 digits returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "Hdfs3",
            "Hdfs3"
        )
        assertThat(result).isFalse()
    }





}