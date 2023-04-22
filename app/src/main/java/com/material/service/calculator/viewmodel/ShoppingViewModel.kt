package com.material.service.calculator.viewmodel


import androidx.lifecycle.*
import com.material.service.calculator.RoomDB.ShoppingItem
import com.material.service.calculator.model.ImageResponse
import com.material.service.calculator.other.Event
import com.material.service.calculator.other.NetworkResult
import com.material.service.calculator.repositories.ShoppingRepositoryInterface
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepositoryInterface) : ViewModel() {

    val shoppingItems = repository.observeAllShoppingItem().asLiveData()

    val totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<Event<NetworkResult<ImageResponse>>>()
    val images: LiveData<Event<NetworkResult<ImageResponse>>> = _images

    private val _currImageUrl = MutableLiveData<String>()
    val currImageUrl: LiveData<String> = _currImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<Event<NetworkResult<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<NetworkResult<ShoppingItem>>> =
        _insertShoppingItemStatus


    fun setCurrentImageUrl(url: String) {
        _currImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, amountString: String, priceString: String) {

    }

    fun searchForImage(imageQuery:String){

    }
}