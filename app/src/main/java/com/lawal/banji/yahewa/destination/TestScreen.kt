package com.lawal.banji.yahewa.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

import com.lawal.banji.yahewa.R

import com.lawal.banji.yahewa.display.CurrentWeatherDisplay
import com.lawal.banji.yahewa.ui.theme.Lavender
import com.lawal.banji.yahewa.weather.view.WeatherViewModel

@Composable
fun TestScreen(viewModel: WeatherViewModel) {
    Scaffold(
        topBar = {},
        bottomBar = {},
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Lavender)
        ) {
            if (viewModel.weatherRecord != null) {
                CurrentWeatherDisplay(
                    weatherRecord = weatherRecord,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text(
                    text = stringResource(id = R.string.weather_data_error),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}