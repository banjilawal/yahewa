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
import com.lawal.banji.yahewa.input.ZipcodeInput
import com.lawal.banji.yahewa.model.ForecastGroupState
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastScreen(
    forecastGroupState: ForecastGroupState,
    onNavigate: (NavigationEvent) -> Unit = {},
    onZipcodeEntered: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .fillMaxWidth().background(PowderBlueGray),
        topBar = { ZipcodeInput(onZipcodeEntered = onZipcodeEntered) },
        bottomBar = {},
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(PowderBlueGray)
        ) {
            when (forecastGroupState) {
                is ForecastGroupState.Loading -> {
                   Text(text = "Loading currentWeather...")
                }
                is ForecastGroupState.Error -> {
                    val errorMessage = "Yahewa currentWeather state error: ${(forecastGroupState as ForecastGroupState.Error).message}"
                    Text(text = errorMessage)
                }
                is ForecastGroupState.Success -> {
                    Text(text = "Success with the forecast group")
//                   (forecastGroupState.forecastGroup.forecasts)
                }
            }
        }
    }
}