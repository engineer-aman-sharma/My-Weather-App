package com.weather.app5.data.repository

import android.util.Log
import com.weather.app5.data.models.WeatherResponse
import com.weather.app5.data.remote.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponse {
        return api.getWeather(latitude, longitude)
    }
}