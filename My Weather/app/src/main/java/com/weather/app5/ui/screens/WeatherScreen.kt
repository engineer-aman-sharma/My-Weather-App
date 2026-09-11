package com.weather.app5.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.weather.app5.data.viewmodel.WeatherState
import com.weather.app5.data.viewmodel.WeatherViewModel
import com.weather.app5.ui.components.ErrorContent
import com.weather.app5.ui.components.LoadingContent
import com.weather.app5.ui.components.WeatherContent

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {
    val state by viewModel.weatherState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (state) {
            WeatherState.Loading -> LoadingContent()

            is WeatherState.Error -> {
                ErrorContent(
                    message = (state as WeatherState.Error).message
                )
            }

            is WeatherState.Success -> {
                val success = state as WeatherState.Success

                WeatherContent(
                    weather = success.weather,
                    city = success.cityName
                )
            }
        }
    }
}