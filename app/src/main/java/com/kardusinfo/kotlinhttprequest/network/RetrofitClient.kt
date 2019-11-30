package com.kardusinfo.kotlinhttprequest.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val BASE_URL = "https://openweathermap.org/data/2.5/"

    fun create() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}