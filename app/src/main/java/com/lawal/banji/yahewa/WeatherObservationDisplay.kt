package com.lawal.banji.yahewa

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeatherObservationDisplay(weatherObservation: WeatherObservation) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Location: ${weatherObservation.location}")
        Text(text = "Current Temperature: ${weatherObservation.currentTemperature}")
        Text(text = "Feels Like: ${weatherObservation.feelsLikeTemperature}")
        Text(text = "Condition: ${weatherObservation.weatherCondition}")
        Text(text = "Low: ${weatherObservation.lowTemperature}")
        Text(text = "High: ${weatherObservation.highTemperature}")
        Text(text = "Humidity: ${weatherObservation.percentHumidity}%")
        Text(text = "Pressure: ${weatherObservation.pressure} hPa")
    }
}