package com.material.service.calculator.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.material.service.calculator.getOrAwaitValueTest
import com.material.service.calculator.other.Constants
import com.material.service.calculator.other.NetworkResult
import com.material.service.calculator.repositories.FakeShoppingRepository

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShoppingViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ShoppingViewModel
    @Before
    fun setup(){
        viewModel= ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun `insert shopping item with empty field, return error`(){
        viewModel.insertShoppingItem("name","","20.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(NetworkResult.Error("",""))
    }

    @Test
    fun `insert shopping item with too long name, return error`(){
        val string = buildString {
            for (i in 1..Constants.MAX_NAME_LENGTH+1){
                append(1)
            }
        }
        viewModel.insertShoppingItem(string,"5","20.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(NetworkResult.Error("",""))
    }


    @Test
    fun `insert shopping item with too long price, return error`(){
        val price = buildString {
            for (i in 1..Constants.MAX_PRICE_LENGTH+1){
                append(1)
            }
        }
        viewModel.insertShoppingItem("name","5",price)

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(NetworkResult.Error("",""))
    }

    @Test
    fun `insert shopping item with too high amount, return error`(){

        viewModel.insertShoppingItem("name","999999999999999999999","3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(NetworkResult.Error("",""))
    }

    @Test
    fun `insert shopping item with valid input, return success`(){

        viewModel.insertShoppingItem("name","99","3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(NetworkResult.Success(""))
    }
}