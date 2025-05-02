package com.lawal.banji.yahewa.destination

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.model.ForecastGroupState
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.ui.theme.BattleShipGrayBlue
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultDisplayBackgroundColor
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.Peach
import com.lawal.banji.yahewa.ui.theme.PowderBlue
import com.lawal.banji.yahewa.utils.WeatherIcon


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastComposable(forecast: Forecast, modifier: Modifier = Modifier) {

    val precipitationProbability = "${forecast.precipitationProbability} % chance of rain"
    val solarTransitionTimes = "sunrise: ${forecast.sunrise} sunset: ${forecast.sunset}"
    val humidity = "${forecast.humidity} % humidity"
    val temperature = "high ${forecast.temperature.max} / low ${forecast.temperature.min}"
    val weatherIconId = forecast.weather[0].iconId
    val weatherDescription = forecast.weather[0].description

    // Use a Column instead of LazyColumn as this is static data
    Column(
        modifier = modifier
            .padding(DefaultPadding)
            .fillMaxWidth()
            .background(BattleShipGrayBlue)
            .clip(RoundedCornerShape(DefaultCornerRadius))
    ) {
        Text(
            text = temperature,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(PowderBlue)
                .clip(RoundedCornerShape(DefaultCornerRadius)),
            textAlign = TextAlign.Center
        )
        WeatherIcon(
            iconId = weatherIconId,
            iconSize = 60.dp// Adjust size appropriately
        )

        Text(
            text = solarTransitionTimes,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Peach)
                .clip(RoundedCornerShape(DefaultCornerRadius)),
            textAlign = TextAlign.Center
        )

        Text(
            text = precipitationProbability,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(PowderBlue)
                .clip(RoundedCornerShape(DefaultCornerRadius)),
            textAlign = TextAlign.Center
        )

        Text(
            text = humidity,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Peach)
                .clip(RoundedCornerShape(DefaultCornerRadius)),
            textAlign = TextAlign.Center
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastListComposable(forecasts: List<Forecast>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastGroupScreen(
    forecastGroupState: ForecastGroupState,
    onNavigate: (NavigationEvent) -> Unit = {},
    onZipcodeEntered: (String) -> Unit
) {
    Text(text = "ForecastGroupScreen")

//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize()
//            .statusBarsPadding(),
//        topBar = { ZipcodeInput(onZipcodeEntered = onZipcodeEntered) },
//        bottomBar = {},
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .background(DefaultDisplayBackgroundColor)
//        ) {
//            when (forecastGroupState) {
//                is ForecastGroupState.Loading -> {
//                    // Show loading message
//                    Text(
//                        text = "Loading forecast predictions...",
//                        modifier = Modifier.padding(16.dp)
//                    )
//                }
//
//                is ForecastGroupState.Error -> {
//                    // Show error message
//                    val errorMessage = "WeatherPrediction error: ${(forecastGroupState as ForecastGroupState.Error).message}"
//                    Text(
//                        text = errorMessage,
//                        modifier = Modifier.padding(16.dp),
//                        color = MaterialTheme.colorScheme.error
//                    )
//                }
//
//                is ForecastGroupState.Success -> {
//                    // Ensure the list is constrained properly
//                    ForecastListComposable(
//                        forecasts = forecastGroupState.forecastGroup.forecasts,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f) // Ensures the LazyColumn gets constrained height
//                    )
//                }
//            }
//        }
//    }
}