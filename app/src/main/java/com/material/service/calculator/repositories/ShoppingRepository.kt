package com.material.service.calculator.repositories

import com.material.service.calculator.RoomDB.ShoppingDao
import com.material.service.calculator.RoomDB.ShoppingItem
import com.material.service.calculator.api.PixabayApi
import com.material.service.calculator.api.PixabayService
import com.material.service.calculator.model.ImageResponse
import com.material.service.calculator.other.NetworkResult
import kotlinx.coroutines.flow.Flow

class ShoppingRepository(private val shoppingDao: ShoppingDao, private val  pixabayService:PixabayService):ShoppingRepositoryInterface {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItem(): Flow<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItem()
    }

    override fun observeTotalPrice(): Flow<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): NetworkResult<ImageResponse> {
        return try {
            val response = pixabayService.pixabayApi.searchForImage(imageQuery)
                if(response.isSuccessful){
                    response.body()?.let {
                        return@let NetworkResult.Success(it)
                    }?:NetworkResult.Error("An unknow error occured",null)
                }else{
                    NetworkResult.Error("An unknow error occured",null)
                }

        }catch (e:Exception){
            return NetworkResult.Error("Couldn't Reach The Server. Check Your Internet Connection",null)
        }
    }

}