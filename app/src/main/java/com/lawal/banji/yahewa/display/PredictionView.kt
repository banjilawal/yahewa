package com.lawal.banji.yahewa.display


import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.LargeIconSize
import com.lawal.banji.yahewa.ui.theme.SmallerPadding
import com.lawal.banji.yahewa.ui.theme.White
import com.lawal.banji.yahewa.utils.iconFromWeatherApiId


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PredictionView(weatherPrediction: com.lawal.banji.yahewa.model.WeatherPrediction) {

    val maxTemp = weatherPrediction.temperature.max
    val minTemp = weatherPrediction.temperature.min
    val precipitationProbability = "$weatherPrediction.probabilityOfPrecipitation % chance of rain"
    val sunrise  = "sunrise: ${weatherPrediction.sunrise}"
    val sunset  = "sunset: ${weatherPrediction.sunset}"
    val humidity = "Humidity ${weatherPrediction.humidity} %"


    val iconId = weatherPrediction.weather[0].iconId
    val description = weatherPrediction.weather[0].description
    val weather = weatherPrediction.weather[0]

    val temperature = "high $maxTemp / low $minTemp"

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
                text = temperature,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxSize().padding(bottom = 0.dp),
                textAlign = TextAlign.Center
            )
        }
        // Row containing the temperature and weather icon
        item {
            androidx.compose.foundation.layout.Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 0.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {

                // Weather icon
                Box(
                    modifier = Modifier
                        .weight(0.4f)
//                        .padding(SmallPadding)
                        .align(CenterVertically)
                ) {
                    iconFromWeatherApiId(iconId, backgroundColor = White, iconSize = LargeIconSize)
                }
            }
        }
        item {
            Text(
                text = "$sunrise $sunset",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallerPadding)
            )
        }
        item {
            Text(
                text = precipitationProbability,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallerPadding)
            )
        }
        item {
            Text(
                text = humidity,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallerPadding)
            )
        }
    }
}