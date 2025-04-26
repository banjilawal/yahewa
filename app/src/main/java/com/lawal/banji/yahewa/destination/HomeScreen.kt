package com.lawal.banji.yahewa.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.lawal.banji.yahewa.display.CurrentForecastView
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.ui.theme.Lavender
import com.lawal.banji.yahewa.utils.TextBox
import com.lawal.banji.yahewa.viewmodel.ForecastState


@Composable
fun HomeScreen(
    forecastState: ForecastState,
    onNavigate: (NavigationEvent) -> Unit = {} //
) {
    Scaffold(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        topBar = {
            TextBox(text = stringResource(id = com.lawal.banji.yahewa.R.string.app_name), style = MaterialTheme.typography.titleMedium,)
//            Text(
//                modifier = Modifier.background(SandLight).fillMaxWidth(),
//                style  = MaterialTheme.typography.titleLarge,
//                text = stringResource(id = com.lawal.banji.yahewa.R.string.app_name))
        },
        bottomBar = {},
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Lavender)
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
                    CurrentForecastView(forecastState.forecast)
//                    val forecast = forecastState.forecast
//                    Text(text = forecast.city,  style = MaterialTheme.typography.bodyMedium,)
//                    Text(text = "Icon ID ${forecast.weather[0].iconId}",  style = MaterialTheme.typography.bodyMedium)
//                    Text(text = "Weather ${forecast.weather[0].description}")
//                    Text(text = "Temperature ${forecast.main.temperature}",  style = MaterialTheme.typography.bodyMedium)
//                    Text(text = "Low ${forecast.main.lowTemperature}",  style = MaterialTheme.typography.bodyMedium)
//                    Text(text = "High  ${forecast.main.highTemperature}",  style = MaterialTheme.typography.bodyMedium)
//                    Text(text = "Humidity ${forecast.main.percentHumidity}",  style = MaterialTheme.typography.bodyMedium)
//                    Text(text = "Pressure ${forecast.main.pressure}",  style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}