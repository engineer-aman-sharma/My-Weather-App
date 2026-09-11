package com.weather.app5.data.remote

import com.weather.app5.data.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "temperature_2m,weather_code",
        @Query("hourly") hourly: String = "temperature_2m",
        @Query("daily") daily: String =
            "weather_code,temperature_2m_max,temperature_2m_min"
    ): WeatherResponse
}