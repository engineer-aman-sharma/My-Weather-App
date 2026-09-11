package com.weather.app5.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.app5.data.location.LocationProvider
import com.weather.app5.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationProvider: LocationProvider
) : ViewModel() {

    private val _weatherState =
        MutableStateFlow<WeatherState>(WeatherState.Loading)

    val weatherState: StateFlow<WeatherState> =
        _weatherState.asStateFlow()

    fun getWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _weatherState.value = WeatherState.Loading
            try {
                val weather = repository.getWeather(
                    latitude,
                    longitude
                )

                val cityName = locationProvider.getCityName(
                    latitude,
                    longitude
                )

                _weatherState.value = WeatherState.Success(
                    weather = weather,
                    cityName = cityName
                )

            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(
                    e.message ?: "Something went wrong"
                )
            }
        }
    }

    fun loadWeather() {
        viewModelScope.launch {
            val location = locationProvider.getCurrentLocation()

            if (location != null) {
                getWeather(
                    location.latitude,
                    location.longitude
                )
            }
        }
    }
}