package com.material.service.calculator.repositories

import com.material.service.calculator.RoomDB.ShoppingItem
import com.material.service.calculator.model.ImageResponse
import com.material.service.calculator.other.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ShoppingRepositoryInterface {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItem(): Flow<List<ShoppingItem>>

    fun observeTotalPrice():Flow<Float>


}