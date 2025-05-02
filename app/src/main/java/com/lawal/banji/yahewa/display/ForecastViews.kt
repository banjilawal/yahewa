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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.input.ZipcodeInput
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.model.ForecastGroup
import com.lawal.banji.yahewa.model.ForecastGroupState
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultDisplayBackgroundColor
import com.lawal.banji.yahewa.ui.theme.DefaultIconSize
import com.lawal.banji.yahewa.ui.theme.LargeCornerRadius
import com.lawal.banji.yahewa.ui.theme.PowderBlue
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray
import com.lawal.banji.yahewa.ui.theme.SandLighter
import com.lawal.banji.yahewa.ui.theme.SandLightest
import com.lawal.banji.yahewa.ui.theme.Silver
import com.lawal.banji.yahewa.utils.WeatherIcon
import com.lawal.banji.yahewa.utils.toDateTimeString

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastViewComposable(forecast: Forecast, modifier: Modifier = Modifier) {

    val precipitationProbability = "${forecast.precipitationProbability * 100} % chance of rain"
    val solarTransitionTimes = "sunrise: ${forecast.sunrise} sunset: ${forecast.sunset}"
    val humidity = "${forecast.humidity} % humidity"
    val temperatureRange = "hi ${forecast.temperature.max} / ${forecast.temperature.min} lo"
    val iconId = forecast.weather[0].iconId
    val description = forecast.weather[0].description
    val forecastDate = forecast.sunset.toDateTimeString()


    LazyColumn(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxSize()
            .background(PowderBlueGray, RoundedCornerShape(DefaultCornerRadius))
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Ensure the Text spans the full width
                    .fillMaxHeight(0.16f)
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .background(
                        SandLighter,
                        RoundedCornerShape(DefaultCornerRadius)
                    )
                    .clip(RoundedCornerShape(DefaultCornerRadius))
            ){
                Column() {
                    Text(
                        text = forecastDate,
                        textAlign = TextAlign.Center, // Center-align the text horizontally
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth() // Ensure the Text spans the full width
                            .fillMaxHeight(0.8f)
                            .padding(
                                horizontal = 5.dp,
                                vertical = 5.dp
                            ) // Add the needed 5px horizontal and vertical padding
                            .background(
                                SandLighter,
                                RoundedCornerShape(DefaultCornerRadius)
                            ) // Background color for the Text
                            .clip(RoundedCornerShape(DefaultCornerRadius)) // Rounded corners for the background
                    )
                    Text(
                        text = solarTransitionTimes, // Dynamically fetch formatted time
                        textAlign = TextAlign.Center, // Center-align the text horizontally
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth() // Ensure the Text spans the full width
                            .fillMaxHeight(0.2f)
                            .padding(
                                horizontal = 5.dp,
                                vertical = 0.dp
                            ) // Add the needed 5px horizontal and vertical padding
                            .background(
                                SandLightest,
                                RoundedCornerShape(DefaultCornerRadius)
                            ) // Background color for the Text
                            .clip(RoundedCornerShape(DefaultCornerRadius)) // Rounded corners for the background
                    )
                }

            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillParentMaxHeight(0.73f) // Adjust height as needed
                    .padding(0.dp)
                    .background(Silver) // Background for the icon
                    .clip(RoundedCornerShape(LargeCornerRadius)) // Rounded corners for the entire Box
            ) {
                // Weather Icon
                WeatherIcon(
                    iconId = iconId,
                    contentDescription = description,
                    iconSize = DefaultIconSize,
                    modifier = Modifier
                        .fillMaxSize() // Ensure the WeatherIcon fills the entire Box
                )

                // Current Temperature Text (Top-Center)
                Text(
                    text = temperatureRange,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter) // Align to the top center
                        .padding(top = 8.dp) // Small padding to avoid sticking to the top edge
                        .background(
                            PowderBlue.copy(alpha = 0.0f), // Transparent overlay background
                            RoundedCornerShape(DefaultCornerRadius)
                        ) // Rounded corners for the Text
                        .clip(RoundedCornerShape(DefaultCornerRadius)) // Apply rounded corners
                )

                // Weather Description Text (Bottom-Center)
                Text(
                    text = precipitationProbability,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter) // Align to the bottom center
                        .padding(bottom = 8.dp) // Small padding to avoid sticking to the bottom edge
                        .background(
                            PowderBlue.copy(alpha = 0.0f), // Transparent overlay background
                            RoundedCornerShape(DefaultCornerRadius)
                        ) // Rounded corners for the Text
                        .clip(RoundedCornerShape(DefaultCornerRadius)) // Apply rounded corners
                )
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        @Composable
        fun ForecastGroupView(
            forecastGroupState: ForecastGroupState,
            onNavigate: (NavigationEvent) -> Unit = {},
            onZipcodeEntered: (String) -> Unit
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                topBar = { ZipcodeInput(onZipcodeEntered = onZipcodeEntered) },
                bottomBar = {},
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(DefaultDisplayBackgroundColor)
                ) {
                    when (forecastGroupState) {
                        is ForecastGroupState.Loading -> {
                            // Show loading message
                            Text(
                                text = "Loading currentWeather predictions...",
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                        is ForecastGroupState.Error -> {
                            // Show error message
                            val errorMessage = "Forecast error: ${forecastGroupState.message}"
                            Text(
                                text = errorMessage,
                                modifier = Modifier.padding(16.dp),
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        is ForecastGroupState.Success -> {
                            // Ensure the list is constrained properly
                            if (forecastGroupState.forecastGroup.forecasts.isNotEmpty()) {
                                ForecastViewComposable(
                                    forecast = forecastGroupState.forecastGroup.forecasts[0],
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp) // Add padding for proper spacing
                                )
                            } else {
                                Text(
                                    text = "No forecasts available.",
                                    style = MaterialTheme.typography.labelMedium,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

//        item {
//            Text(
//                text = temperatureRange,
//                textAlign = TextAlign.Center, // Center-align the text horizontally
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier
//                    .fillMaxWidth() // Ensure the Text spans the full width
//                    .fillMaxHeight(0.23f)
//                    .padding(
//                        horizontal = 32.dp,
//                        vertical = 5.dp
//                    ) // Add the needed 5px horizontal and vertical padding
//                    .background(
//                        Silver,
//                        RoundedCornerShape(DefaultCornerRadius)
//                    ) // Background color for the Text
//                    .clip(RoundedCornerShape(LargeCornerRadius)) // Rounded corners for the background
//            )
//        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PredictionGroupComposable(forecastGroup: ForecastGroup) {

    val cityDetails = "$forecastGroup.city.name, ${forecastGroup.city.country}"
    val coordinates = "lat:  ${forecastGroup.city.coordinates.latitude}, " +
            "lon: ${forecastGroup.city.coordinates.longitude}"

    LazyColumn() {
        item { Box(modifier = Modifier.fillMaxWidth().background(SandLightest)) { Text(text = cityDetails) } }
//        item { Box(modifier = Modifier.fillMaxWidth().background(Lavender)) { Text(text = coordinates) } }
//        item { PredictionListComposable(predictions = forecastGroup.predictions) }
    }
}