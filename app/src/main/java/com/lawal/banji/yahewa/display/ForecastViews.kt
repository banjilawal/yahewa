package com.lawal.banji.yahewa.display

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.model.ForecastRecord
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.ui.theme.DarkGray1
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.LargeCornerRadius
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray
import com.lawal.banji.yahewa.ui.theme.SandLighter
import com.lawal.banji.yahewa.ui.theme.SandLightest
import com.lawal.banji.yahewa.ui.theme.Silver
import com.lawal.banji.yahewa.ui.theme.SmallIconSize
import com.lawal.banji.yahewa.ui.theme.SmallPadding
import com.lawal.banji.yahewa.utils.WeatherIcon
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastComposable(forecastRecord: ForecastRecord, modifier: Modifier = Modifier) {

    val precipitationProbability = "${forecastRecord.precipitationProbability * 100} % chance of rain"
    val solarTransitionTimes = "sunrise: ${forecastRecord.sunrise} sunset: ${forecastRecord.sunset}"
    val humidity = "${forecastRecord.humidity} % humidity"
    val temperatureRange = "hi ${forecastRecord.temperature.max} / ${forecastRecord.temperature.min} lo"
    val iconId = forecastRecord.weatherItems[0].iconId
    val description = forecastRecord.weatherItems[0].description
    val dateString = Instant.ofEpochMilli(forecastRecord.sunset * 1000)
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy", Locale.getDefault()))


    Column(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth()
            .background(PowderBlueGray, RoundedCornerShape(DefaultCornerRadius))
    ) {
        // Date and solar transition times
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .background(SandLighter, RoundedCornerShape(DefaultCornerRadius))
                .padding(0.dp)
        ) {
            Column {
                Text(
                    text = dateString,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Icon and temperature details
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(Silver, RoundedCornerShape(LargeCornerRadius))
        ) {
            WeatherIcon(
                iconId = iconId,
                contentDescription = description,
                iconSize = SmallIconSize,
                modifier = Modifier.align(Alignment.Center)
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
            )
            Text(
                text = temperatureRange,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)

            )
            Text(
                text = precipitationProbability,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
            )
        }
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun ForecastListComposable(forecastRecords: List<ForecastRecord>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight() // Ensures the LazyColumn occupies the entire height
            .background(DarkGray1),
        horizontalAlignment = Alignment.CenterHorizontally // Align items horizontally at the center

    ) {
        items(forecastRecords.size) { index ->
            ForecastComposable(
                forecastRecord = forecastRecords[index],
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.5f) // Adjust height as needed
                    .padding(vertical = SmallPadding) // Add space between items
            )
        }
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun ForecastGroupComposable(
    forecast: Forecast,
    modifier: Modifier = Modifier
) {
    val totalForecasts = forecast.numberOfForecasts
    val title = "$totalForecasts day Forecasts for ${forecast.city.name}, ${forecast.city.country}"
    val coordinates = "lat: ${forecast.city.coordinate.latitude}, " +
            "lon: ${forecast.city.coordinate.longitude}"

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(SandLightest)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f)
                .background(Color.Transparent)
                .padding(0.dp)
               // .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // ForecastRecord List Section (Scrollable)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Fills remaining vertical space
        ) {
            ForecastListComposable(
                forecastRecords = forecast.forecastRecords,
                modifier = Modifier.fillMaxSize().padding(0.dp)
            )
        }
    }
}


