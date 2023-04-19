package com.material.service.calculator.api

import com.material.service.calculator.other.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PixabayService {
    val pixabayApi:PixabayApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        pixabayApi=retrofit.create(PixabayApi::class.java)
    }
}