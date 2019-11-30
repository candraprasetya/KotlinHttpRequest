package com.kardusinfo.kotlinhttprequest.network

import com.kardusinfo.kotlinhttprequest.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") app_id: String
    ): Call<WeatherResponse>
}