package com.lawal.banji.yahewa.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.lawal.banji.yahewa.R
import com.lawal.banji.yahewa.ui.theme.Lavender
import com.lawal.banji.yahewa.viewmodel.ForecastState
import com.lawal.banji.yahewa.viewmodel.ForecastViewModel

@Composable
fun TestScreen(viewModel: ForecastViewModel) {

    val forecastState = viewModel.forecastState.collectAsState().value


    Scaffold(
        topBar = {
            Text(text = stringResource(id = R.string.app_name))
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
                    val errorMessage = (forecastState as ForecastState.Error).message
                    Text(text = errorMessage)
                }
                is ForecastState.Success -> {
                    val forecast = forecastState.forecast
                    Text(text = forecast.city)
                    Text(text = "Temperature ${forecast.main.temperature}")
                    Text(text = "Low ${forecast.main.lowTemperature}")
                    Text(text = "High  ${forecast.main.highTemperature}")
                    Text(text = "Humidity ${forecast.main.percentHumidity}")
                    Text(text = "Pressure ${forecast.main.pressure}")
                }
            }
        }
    }
}