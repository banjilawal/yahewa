package com.lawal.banji.yahewa.display

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultDisplayBackgroundColor
import com.lawal.banji.yahewa.ui.theme.DefaultIconSize
import com.lawal.banji.yahewa.ui.theme.LargeCornerRadius
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray
import com.lawal.banji.yahewa.ui.theme.SandLighter
import com.lawal.banji.yahewa.ui.theme.Silver
import com.lawal.banji.yahewa.utils.WeatherIcon
import com.lawal.banji.yahewa.utils.toDateTimeString

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastComposable(forecast: Forecast, modifier: Modifier = Modifier) {

    val precipitationProbability = "${forecast.precipitationProbability * 100} % chance of rain"
    val solarTransitionTimes = "sunrise: ${forecast.sunrise} sunset: ${forecast.sunset}"
    val humidity = "${forecast.humidity} % humidity"
    val temperatureRange = "hi ${forecast.temperature.max} / ${forecast.temperature.min} lo"
    val iconId = forecast.weather[0].iconId
    val description = forecast.weather[0].description
    val forecastDate = forecast.sunset.toDateTimeString()

    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(PowderBlueGray, RoundedCornerShape(DefaultCornerRadius))
    ) {
        // Date and solar transition times
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SandLighter, RoundedCornerShape(DefaultCornerRadius))
                .padding(5.dp)
        ) {
            Column {
                Text(
                    text = forecastDate,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = solarTransitionTimes,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Icon and temperature details
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Silver, RoundedCornerShape(LargeCornerRadius))
                .padding(8.dp)
        ) {
            WeatherIcon(
                iconId = iconId,
                contentDescription = description,
                iconSize = DefaultIconSize,
                modifier = Modifier.align(Alignment.Center)
            )
            Text(
                text = temperatureRange,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp)
            )
            Text(
                text = precipitationProbability,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun ForecastListComposable(forecasts: List<Forecast>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight() // Ensures the LazyColumn occupies the entire height
            .background(DefaultDisplayBackgroundColor) // Optional background for the list
    ) {
        items(forecasts.size) { index ->
            ForecastComposable(
                forecast = forecasts[index],
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Add space between items
            )
        }
    }
}


