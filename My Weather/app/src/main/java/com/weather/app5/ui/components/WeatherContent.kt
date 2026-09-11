package com.weather.app5.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weather.app5.data.models.WeatherResponse
import com.weather.app5.ui.utils.weatherIcon
import kotlinx.coroutines.delay
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherContent(
    weather: WeatherResponse,
    city: String
) {
    val current = weather.current
    val hourly = weather.hourly
    val daily = weather.daily

    val time by produceState(initialValue = "") {
        while (true) {
            value = LocalTime.now()
                .format(DateTimeFormatter.ofPattern("hh:mm a"))
            delay(1_000)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Row(modifier = Modifier.fillMaxWidth()) {

                            Text(
                                text = "📍$city : ",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = time,
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )


                        }
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Current weather by Aman Sharma",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 13.dp)
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(
                                MaterialTheme.colorScheme.primary
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = weatherIcon(current?.weatherCode),
                            fontSize = 24.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 8.dp,
                end = 16.dp,
                bottom = 32.dp
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            item {
                CurrentWeatherCard(
                    temperature = current?.temperature,
                    weatherCode = current?.weatherCode
                )
            }

            item {
                WeatherDetails(
                    humidity = current?.humidity,
                    windSpeed = current?.windSpeed
                )
            }

            item {
                SectionTitle(
                    title = "Hourly forecast",
                    subtitle = "Next 24 hours"
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 2.dp)
                ) {
                    items(
                        hourly?.time?.indices
                            ?.take(24)
                            ?.toList()
                            ?: emptyList()
                    ) { index ->

                        HourlyWeatherCard(
                            time = hourly?.time?.getOrNull(index),
                            temperature = hourly?.temperature?.getOrNull(index),
                            weatherCode = hourly?.weatherCode?.getOrNull(index)
                        )
                    }
                }
            }

            item {
                SectionTitle(
                    title = "7-day forecast",
                    subtitle = "Daily temperature outlook"
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(
                            horizontal = 18.dp,
                            vertical = 8.dp
                        )
                    ) {
                        daily?.time
                            ?.indices
                            ?.take(7)
                            ?.forEach { index ->

                                DailyWeatherRow(
                                    date = daily.time.getOrNull(index),
                                    minTemperature = daily.minTemperature.getOrNull(index),
                                    maxTemperature = daily.maxTemperature.getOrNull(index),
                                    weatherCode = daily.weatherCode.getOrNull(index)
                                )

                                if (index < 6) {
                                    HorizontalDivider(
                                        color = MaterialTheme.colorScheme.outlineVariant
                                    )
                                }
                            }
                    }
                }
            }
        }
    }
}