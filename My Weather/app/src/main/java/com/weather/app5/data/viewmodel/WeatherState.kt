package com.weather.app5.data.viewmodel

import com.weather.app5.data.models.WeatherResponse

sealed class WeatherState {
    data object Loading : WeatherState()

    data class Success(
        val weather: WeatherResponse,
        val cityName: String
    ) : WeatherState()

    data class Error(
        val message: String
    ) : WeatherState()
}