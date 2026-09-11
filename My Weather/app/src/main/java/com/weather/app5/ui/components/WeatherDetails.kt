package com.weather.app5.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.weather.app5.ui.utils.formatTemperature

@Composable
fun WeatherDetails(
    humidity: Int?,
    windSpeed: Double?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        WeatherDetailCard(
            modifier = Modifier.weight(1f),
            icon = "💧",
            title = "Humidity",
            value = "${humidity ?: "--"}%"
        )

        WeatherDetailCard(
            modifier = Modifier.weight(1f),
            icon = "💨",
            title = "Wind",
            value = "${windSpeed?.formatTemperature() ?: "--"} km/h"
        )
    }
}