package com.lawal.banji.yahewa

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeatherDetailsDisplay(weatherResponse: OpenWeatherResponse) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Location: ${weatherResponse.locationName}",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Current Temperature: ${weatherResponse.main.temperature}°",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Feels Like: ${weatherResponse.main.temperatureFeelsLike}°",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "High: ${weatherResponse.main.highTemperature}°",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Low: ${weatherResponse.main.lowTemperature}°",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Humidity: ${weatherResponse.main.percentHumidity}%",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Pressure: ${weatherResponse.main.pressure} hPa",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}