package com.lawal.banji.yahewa.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.White
import com.lawal.banji.yahewa.utils.iconFromWeatherApiId


@Composable
fun CurrentForecastView(forecast: Forecast) {
    val city = forecast.city
    val currentTemperature = "${forecast.main.temperature}°"
    val temperatureFeelsLike = "Feels like ${forecast.main.temperatureFeelsLike}°"
    val lowTemperature = "Low ${forecast.main.lowTemperature}°"
    val highTemperature = "High ${forecast.main.highTemperature}°"
    val humidity = "% Humidity ${forecast.main.percentHumidity}"
    val pressure = "Pressure ${forecast.main.pressure} inHg"
    val iconId = forecast.weather[0].iconId
    val description = forecast.weather[0].description

    val weather = forecast.weather[0]
    LazyColumn(
        modifier = Modifier
            .padding(DefaultPadding)
            .fillMaxSize()
            .background(White)
    ) {
            item { Text(text = city, style = MaterialTheme.typography.bodyMedium) }
            item {
                LazyRow(){
                    item{ Text(text  = currentTemperature) }
                    item{ iconFromWeatherApiId(iconId) }
                }
            }
        item { Text(text = temperatureFeelsLike, style = MaterialTheme.typography.bodyMedium) }
            item { Text(text = lowTemperature, style = MaterialTheme.typography.bodyMedium) }
            item { Text(text = highTemperature, style = MaterialTheme.typography.bodyMedium)  }
            item { Text(text = humidity, style = MaterialTheme.typography.bodyMedium)  }
            item { Text(text = pressure, style = MaterialTheme.typography.bodyMedium) }
        }

}