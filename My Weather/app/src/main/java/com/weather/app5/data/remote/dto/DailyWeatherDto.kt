package com.weather.app5.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DailyWeatherDto(
    val time: List<String>,

    @SerializedName("weather_code")
    val weatherCode: List<Int>,

    @SerializedName("temperature_2m_max")
    val maxTemperature: List<Double>,

    @SerializedName("temperature_2m_min")
    val minTemperature: List<Double>
)