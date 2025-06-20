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
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.display.ForecastGroupComposable
import com.lawal.banji.yahewa.input.ZipCodeInput
import com.lawal.banji.yahewa.model.ForecastState
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastScreen(
    forecastState: ForecastState,
    onNavigate: (NavigationEvent) -> Unit = {},
    onZipcodeEntered: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(PowderBlueGray),
        topBar = { ZipCodeInput(onZipCodeEntered = onZipcodeEntered) },
        bottomBar = {}, // Optional bottom bar implementation
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(PowderBlueGray)
        ) {
            when (forecastState) {
                is ForecastState.Loading -> {
                    Text(
                        text = "Loading currentWeather...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }

                is ForecastState.Error -> {
                    val errorMessage = "Yahewa currentWeather state error: ${(forecastState as ForecastState.Error).message}"
                    Text(
                        text = errorMessage,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(DefaultPadding)
                    )
                }

                is ForecastState.Success -> {
                    // Ensure ForecastListComposable fills the remaining vertical space
                    ForecastGroupComposable(
                        forecast = forecastState.forecast,
                        modifier = Modifier.fillMaxSize()
                            .padding((0.dp)
                    )
//                    ForecastListComposable(
//                        forecastRecords = forecastState.forecast.forecastRecords,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f) // Ensures LazyColumn occupies the remaining space
                    )
                }
            }
        }
    }
}
