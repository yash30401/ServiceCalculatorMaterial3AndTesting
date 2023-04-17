package com.material.service.calculator

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.material.service.calculator.Testing.ResourceComparer
import org.junit.Before

import org.junit.Test

class ResourceComparerTest{

    private lateinit var resourceComparer: ResourceComparer

    @Before
    fun setup(){
        resourceComparer = ResourceComparer()
    }
    @Test
    fun stringResourceSameAsGivenString_returnsTrue(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context,R.string.testingString,"Android")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnsFalse(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context,R.string.testingString,"Fsfas")
        assertThat(result).isFalse()
    }

}