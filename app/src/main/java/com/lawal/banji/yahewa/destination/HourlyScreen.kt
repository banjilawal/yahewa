package com.lawal.banji.yahewa.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.ui.theme.Cerulean
import com.lawal.banji.yahewa.ui.theme.Orange
import com.lawal.banji.yahewa.viewmodel.ForecastState

@Composable
fun HourlyScreen(
    forecastState: ForecastState,
    onNavigate: (NavigationEvent) -> Unit // Callback t
) {
    Scaffold(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        topBar = {
            Text(modifier = Modifier.background(Cerulean).fillMaxWidth(), text = "Hourly Forecast")
        },
        bottomBar = {},
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Orange)
        ) {
            when (forecastState) {
                is ForecastState.Loading -> {
                    Text(text = "Loading forecast...")
                }
                is ForecastState.Error -> {
                    val errorMessage = "Yahewa forecast state error: ${(forecastState as ForecastState.Error).message}"
                    Text(text = errorMessage)
                }
                is ForecastState.Success -> {
                    val forecast = forecastState.forecast
                    Text(text = "cloudiness ${forecast.clouds.all}")
                }
            }
        }
    }
}
