package com.lawal.banji.yahewa.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.LargeIconSize
import com.lawal.banji.yahewa.ui.theme.SmallPadding
import com.lawal.banji.yahewa.ui.theme.White
import com.lawal.banji.yahewa.utils.iconFromWeatherApiId


@Composable
fun CurrentForecastView(forecast: Forecast) {
    val city = forecast.city
    val currentTemperature = "${forecast.main.temperature}°"
    val temperatureFeelsLike = "Feels like ${forecast.main.temperatureFeelsLike}°"
    val lowTemperature = "Low ${forecast.main.lowTemperature}°"
    val highTemperature = "High ${forecast.main.highTemperature}°"
    val humidity = "Humidity ${forecast.main.percentHumidity} %"
    val pressure = "Pressure ${forecast.main.pressure} hPa"
    val iconId = forecast.weather[0].iconId
    val description = forecast.weather[0].description

    val weather = forecast.weather[0]
    LazyColumn(
        modifier = Modifier
            .padding(DefaultPadding)
            .fillMaxSize()
            .background(White)
    ) {
            // Row containing City
            item {
                // City needs to be centered. Fills the width of it's center
                Text(
                    text = city,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxSize().padding(DefaultPadding),
                    textAlign = TextAlign.Center
                )
            }
        // Row containing the temperature and weather icon
        item {
            androidx.compose.foundation.layout.Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SmallPadding),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                // Column for temperature data
                androidx.compose.foundation.layout.Column(
                    modifier = Modifier
                        .weight(0.6f) // Reduce weight so the column doesn't use too much space
                        .padding(SmallPadding)
                ) {
                    Text(
                        text = currentTemperature,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center, // Align to start for compact content
                        modifier = Modifier.fillMaxWidth().padding(bottom = SmallPadding)
                    )
                    Text(
                        text = temperatureFeelsLike,
                        style = MaterialTheme.typography.bodySmall, // Keep font size consistent
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(bottom = DefaultPadding)
                    )
                }

                // Weather icon
                Box(
                    modifier = Modifier
                        .weight(0.4f) // Icon takes 30% of the width
//                        .padding(SmallPadding)
                        .align(CenterVertically)
                ) {
                    iconFromWeatherApiId(iconId, backgroundColor = White, iconSize = LargeIconSize)
                }
            }
        }


//            item {
//                LazyRow(){
//                    item { Text(text  = currentTemperature) }
//                    item{ iconFromWeatherApiId(iconId) }
//                }
//            }
//             item { Text(text = temperatureFeelsLike, style = MaterialTheme.typography.bodyMedium) }
            item { Text(text = lowTemperature, style = MaterialTheme.typography.bodyMedium) }
            item { Text(text = highTemperature, style = MaterialTheme.typography.bodyMedium)  }
            item { Text(text = humidity, style = MaterialTheme.typography.bodyMedium)  }
            item { Text(text = pressure, style = MaterialTheme.typography.bodyMedium) }
        }
}