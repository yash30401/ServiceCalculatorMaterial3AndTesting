package com.material.service.calculator.viewmodel


import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.material.service.calculator.RoomDB.ShoppingDatabase
import com.material.service.calculator.RoomDB.ShoppingItem
import com.material.service.calculator.model.ImageResponse
import com.material.service.calculator.other.Constants
import com.material.service.calculator.other.Event
import com.material.service.calculator.other.NetworkResult
import com.material.service.calculator.repositories.ShoppingRepository
import com.material.service.calculator.repositories.ShoppingRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ShoppingRepository

    private val _images = MutableLiveData<Event<NetworkResult<ImageResponse>>>()
    val images: LiveData<Event<NetworkResult<ImageResponse>>> = _images

    private val _currImageUrl = MutableLiveData<String>()
    val currImageUrl: LiveData<String> = _currImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<Event<NetworkResult<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<NetworkResult<ShoppingItem>>> =
        _insertShoppingItemStatus

    init {
        val dao = ShoppingDatabase.getDatabase(application).shoppingDao()
        repository = ShoppingRepository(dao)

    }
    val shoppingItems = repository.observeAllShoppingItem().asLiveData()
    val totalPrice = repository.observeTotalPrice().asLiveData()

    fun setCurrentImageUrl(url: String) {
        _currImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, amountString: String, priceString: String) {
        if (name.isEmpty() || amountString.isEmpty() || priceString.isEmpty()) {
            _insertShoppingItemStatus.postValue(Event(NetworkResult.Error("", null)))
            return
        }
        if (name.length > Constants.MAX_NAME_LENGTH) {
            _insertShoppingItemStatus.postValue(Event(NetworkResult.Error("", null)))
            return
        }

        if (priceString.length > Constants.MAX_PRICE_LENGTH) {
            _insertShoppingItemStatus.postValue(Event(NetworkResult.Error("Price To High", null)))
            return
        }

        val amount = try {
            amountString.toInt()
        } catch (e: Exception) {
            _insertShoppingItemStatus.postValue(Event(NetworkResult.Error("", null)))
            return
        }

        val shoppingItem =
            ShoppingItem(name, amount, priceString.toFloat(), _currImageUrl.value ?: "")
        insertShoppingItemIntoDb(shoppingItem)
        setCurrentImageUrl("")
        _insertShoppingItemStatus.postValue(Event(NetworkResult.Success(shoppingItem)))
    }

//    fun searchForImage(imageQuery: String) {
//        if (imageQuery.isEmpty()) {
//                return
//        }
//        _images.value = Event(NetworkResult.Loading())
//        viewModelScope.launch {
//            val response = repository.searchForImage(imageQuery)
//            _images.value = Event(response)
//        }
//    }
}