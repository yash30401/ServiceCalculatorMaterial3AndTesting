package com.material.service.calculator.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.material.service.calculator.model.ImageResponse
import com.material.service.calculator.other.Event
import com.material.service.calculator.other.NetworkResult
import com.material.service.calculator.repositories.ShoppingRepository
import com.material.service.calculator.repositories.ShoppingRepositoryInterface

class ShoppingViewModel(private val repository: ShoppingRepositoryInterface) : ViewModel() {

    val shoppingItems = repository.observeAllShoppingItem().asLiveData()

    val totalPrice = repository.observeTotalPrice()

    private val _currImageUrl = MutableLiveData<String>()
    val currImageUrl:LiveData<String> = _currImageUrl

}