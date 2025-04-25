package com.lawal.banji.yahewa.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.Lavender

// Updated CurrentForecastView
@Composable
fun CurrentForecastView(forecast: Forecast) {
    LazyColumn(
        modifier = Modifier
            .padding(DefaultPadding)
            .fillMaxSize()
            .background(Lavender)
    ) {
        item { Text(text = forecast.city) }
        item { Text(text = "Icon ID: ${forecast.weather[0].iconId}") }
        item { Text(text = "Weather: ${forecast.weather[0].description}") }
        item { Text(text = "Temperature: ${forecast.main.temperature}°C") }
        item { Text(text = "Low: ${forecast.main.lowTemperature}°C") }
        item { Text(text = "High: ${forecast.main.highTemperature}°C") }
        item { Text(text = "Humidity: ${forecast.main.percentHumidity}%") }
        item { Text(text = "Pressure: ${forecast.main.pressure} hPa") }
    }
}
