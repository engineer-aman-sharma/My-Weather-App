package com.weather.app5.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HourlyWeatherDto(
    val time: List<String>,

    @SerializedName("temperature_2m")
    val temperature: List<Double>,

    @SerializedName("weather_code")
    val weatherCode: List<Int>
)

