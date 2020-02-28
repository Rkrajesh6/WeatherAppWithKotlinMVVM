package com.example.weatherapp.network

import com.example.weatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by rajeshkumar07 on 28-02-2020.
 */
interface ApiServices {
    @GET("forecast")
    suspend fun getWeatherList(
        @Query("zip") zipecode : String,
        @Query("appid") appId : String = "2cba5e7343bb1a4ae8002ab66aca23c9"
    ) : Response<WeatherResponse>
}