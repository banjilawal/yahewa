package com.lawal.banji.yahewa.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.Lavender
import com.lawal.banji.yahewa.utils.iconFromWeatherApiId


@Composable
fun CurrentForecastView(forecast: Forecast) {
    LazyColumn(
        modifier = Modifier
            .padding(DefaultPadding)
            .fillMaxSize()
            .background(Lavender)
    ) {
        item { Text(text = "City: ${forecast.city}", style = MaterialTheme.typography.bodyLarge) }
        item { Text(text = "Icon ID: ${forecast.weather[0].iconId}", style = MaterialTheme.typography.bodyLarge)  }
        item {
           iconFromWeatherApiId(
                weatherApiId = forecast.weather[0].iconId,
                modifier = Modifier.background(Lavender).fillMaxSize(1.0f),
                contentDescription = forecast.weather[0].description
           )
        }
        item { Text(text = "Temperature: ${forecast.main.temperature}°C", style = MaterialTheme.typography.bodyLarge)  }
        item { Text(text = "Low: ${forecast.main.lowTemperature}°C", style = MaterialTheme.typography.bodyLarge) }
        item { Text(text = "High: ${forecast.main.highTemperature}°C", style = MaterialTheme.typography.bodyLarge)  }
        item { Text(text = "Humidity: ${forecast.main.percentHumidity}%", style = MaterialTheme.typography.bodyLarge)  }
        item { Text(text = "Pressure: ${forecast.main.pressure} hPa", style = MaterialTheme.typography.bodyLarge) }
    }
}