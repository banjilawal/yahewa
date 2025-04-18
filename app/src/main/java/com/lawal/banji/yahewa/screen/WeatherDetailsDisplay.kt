package com.lawal.banji.yahewa.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.weather.model.free.WeatherRecord

@Composable
fun WeatherDetailsDisplay(weatherRecord: WeatherRecord) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Location: ${weatherRecord.name}",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Current Temperature: ${weatherRecord.main.temperature}°",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Feels Like: ${weatherRecord.main.temperatureFeelsLike}°",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "High: ${weatherRecord.main.highTemperature}°",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Low: ${weatherRecord.main.lowTemperature}°",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Humidity: ${weatherRecord.main.percentHumidity}%",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Pressure: ${weatherRecord.main.pressure} hPa",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}