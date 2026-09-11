package com.weather.app5.data.models

import com.weather.app5.data.remote.dto.CurrentWeatherDto
import com.weather.app5.data.remote.dto.DailyWeatherDto
import com.weather.app5.data.remote.dto.HourlyWeatherDto

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val current: CurrentWeatherDto?,
    val hourly: HourlyWeatherDto?,
    val daily: DailyWeatherDto?
)