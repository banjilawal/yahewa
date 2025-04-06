package com.lawal.banji.yahewa

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap

@Composable
fun WeatherIcon(icon: Drawable?) {
    if (icon != null) {
        Image(
            bitmap = icon.toBitmap().asImageBitmap(),
            contentDescription = "Weather Icon",
            modifier = Modifier.size(24.dp)
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.farenheit),
            contentDescription = "Default Weather Icon",
            modifier = Modifier
                .size(24.dp)
                .background(Color.White)
        )
    }
}

@Composable
fun WeatherObservationDisplay(weatherObservation: WeatherObservation) {
    Column(modifier = Modifier.padding(16.dp)) {
        WeatherIcon(weatherObservation.icon)
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