package com.material.service.calculator.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.material.service.calculator.RoomDB.ShoppingItem
import com.material.service.calculator.model.ImageResponse
import com.material.service.calculator.other.NetworkResult
import kotlinx.coroutines.flow.Flow

class FakeShoppingRepository :ShoppingRepositoryInterface{


    private val shoppingItems = mutableListOf<ShoppingItem>()

    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value:Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        observableShoppingItems.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float{
        return shoppingItems.sumByDouble {it.price.toDouble()}.toFloat()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override fun observeAllShoppingItem(): Flow<List<ShoppingItem>> {
        return observableShoppingItems.asFlow()
    }

    override fun observeTotalPrice(): Flow<Float> {
        return observableTotalPrice.asFlow()
    }

    override suspend fun searchForImage(imageQuery: String): NetworkResult<ImageResponse> {
        return if(shouldReturnNetworkError){
            NetworkResult.Error("Error",null)
        }else{
            NetworkResult.Success(ImageResponse(listOf(),0,0))
        }
    }
}