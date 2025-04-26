package com.lawal.banji.yahewa.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.SandLight
import com.lawal.banji.yahewa.ui.theme.White
import com.lawal.banji.yahewa.viewmodel.ForecastState


@Composable
fun HomeScreen(
    forecastState: ForecastState,
    onNavigate: (NavigationEvent) -> Unit = {} //
) {
    Scaffold(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        topBar = {
            Box(Modifier.fillMaxWidth().background(SandLight).padding(DefaultPadding)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = com.lawal.banji.yahewa.R.string.app_name),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        },
        bottomBar = {},
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(White)
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
                }
            }
        }
    }
}