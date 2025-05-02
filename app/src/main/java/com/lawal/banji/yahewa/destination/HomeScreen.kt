package com.lawal.banji.yahewa.destination

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.lawal.banji.yahewa.display.CurrentWeatherView
import com.lawal.banji.yahewa.input.ZipCodeInput
import com.lawal.banji.yahewa.model.CurrentWeatherState
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    currentWeatherState: CurrentWeatherState,
    onNavigate: (NavigationEvent) -> Unit = {},
    onZipcodeEntered: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .fillMaxWidth().background(PowderBlueGray),
        topBar = { ZipCodeInput(onZipCodeEntered = onZipcodeEntered) },
        bottomBar = {},
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(PowderBlueGray)
        ) {
            when (currentWeatherState) {
                is CurrentWeatherState.Loading -> {
                   Text(text = "Loading currentWeather...")
                }
                is CurrentWeatherState.Error -> {
                    val errorMessage = "Yahewa currentWeather state error: ${(currentWeatherState as CurrentWeatherState.Error).message}"
                    Text(text = errorMessage)
                }
                is CurrentWeatherState.Success -> {
                   CurrentWeatherView(currentWeatherState.currentWeather)
                }
            }
        }
    }
}