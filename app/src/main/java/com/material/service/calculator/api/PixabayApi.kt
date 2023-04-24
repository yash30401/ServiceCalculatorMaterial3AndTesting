package com.material.service.calculator.api

import com.material.service.calculator.BuildConfig
import com.material.service.calculator.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    //Getting Images From Pixabay Api
    @GET("/api/")
    fun searchForImage(
        @Query("q") searchQuery:String,
        @Query("key") apiKey:String=BuildConfig.API_KEY
    ):Response<ImageResponse>
}