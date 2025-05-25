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
import com.lawal.banji.yahewa.model.ForecastGroupState
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastScreen(
    forecastGroupState: ForecastGroupState,
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
            when (forecastGroupState) {
                is ForecastGroupState.Loading -> {
                    Text(
                        text = "Loading currentWeather...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }

                is ForecastGroupState.Error -> {
                    val errorMessage = "Yahewa currentWeather state error: ${(forecastGroupState as ForecastGroupState.Error).message}"
                    Text(
                        text = errorMessage,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(DefaultPadding)
                    )
                }

                is ForecastGroupState.Success -> {
                    // Ensure ForecastListComposable fills the remaining vertical space
                    ForecastGroupComposable(
                        forecast = forecastGroupState.forecast,
                        modifier = Modifier.fillMaxSize()
                            .padding((0.dp)
                    )
//                    ForecastListComposable(
//                        forecastRecords = forecastGroupState.forecast.forecastRecords,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f) // Ensures LazyColumn occupies the remaining space
                    )
                }
            }
        }
    }
}
