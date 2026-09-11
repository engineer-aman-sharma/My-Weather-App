package com.weather.app5.ui.utils

import com.weather.app5.R

fun formatTime(time: String?): String {
    if (time.isNullOrBlank()) return "--"

    val hour = time
        .substringAfter("T")
        .substringBefore(":")
        .toIntOrNull()
        ?: return time

    return when {
        hour == 0 -> "12 AM"
        hour < 12 -> "$hour AM"
        hour == 12 -> "12 PM"
        else -> "${hour - 12} PM"
    }
}

fun formatDate(date: String?): String {
    if (date.isNullOrBlank()) return "--"

    val parts = date.split("-")

    if (parts.size != 3) return date

    val month = when (parts[1]) {
        "01" -> "Jan"
        "02" -> "Feb"
        "03" -> "Mar"
        "04" -> "Apr"
        "05" -> "May"
        "06" -> "Jun"
        "07" -> "Jul"
        "08" -> "Aug"
        "09" -> "Sep"
        "10" -> "Oct"
        "11" -> "Nov"
        "12" -> "Dec"
        else -> parts[1]
    }

    return "${parts[2]} $month"
}

fun Double.formatTemperature(): String {
    return String.format("%.1f", this)
}

fun weatherCondition(code: Int?): String {
    return when (code) {
        0 -> "Clear sky"
        1 -> "Mainly clear"
        2 -> "Partly cloudy"
        3 -> "Overcast"
        45, 48 -> "Foggy"
        51, 53, 55, 56, 57 -> "Drizzle"
        61, 63, 65, 66, 67 -> "Rain"
        71, 73, 75, 77 -> "Snow"
        80, 81, 82 -> "Rain showers"
        85, 86 -> "Snow showers"
        95 -> "Thunderstorm"
        96, 99 -> "Thunderstorm with hail"
        else -> "Unknown"
    }
}

fun weatherAnm(code: Int?): Int {
    return when (code) {
        1 -> R.raw.clear
        2, 3 -> R.raw.cloudy
        45, 48 -> R.raw.fog
        51, 53, 55, 56, 57 -> R.raw.drizzle
        61, 63, 65, 66, 67, 80, 81, 82 -> R.raw.rainy
        71, 73, 75, 77, 85, 86 -> R.raw.rainy
        95, 96, 99 -> R.raw.thunder
        else -> R.raw.clear
    }
}

fun weatherIcon(code: Int?): String {
    return when (code) {
        0 -> "☀️"
        1 -> "🌤️"
        2 -> "⛅"
        3 -> "☁️"
        45, 48 -> "🌫️"
        51, 53, 55, 56, 57 -> "🌦️"
        61, 63, 65, 66, 67 -> "🌧️"
        71, 73, 75, 77 -> "❄️"
        80, 81, 82 -> "🌦️"
        85, 86 -> "🌨️"
        95, 96, 99 -> "⛈️"
        else -> "🌡️"
    }
}