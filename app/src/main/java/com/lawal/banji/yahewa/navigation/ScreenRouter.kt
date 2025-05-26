package com.lawal.banji.yahewa.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lawal.banji.yahewa.destination.CurrentWeatherScreen
import com.lawal.banji.yahewa.destination.NewForecastScreen
import com.lawal.banji.yahewa.ui.theme.Black
import com.lawal.banji.yahewa.ui.theme.DefaultDisplayBackgroundColor
import com.lawal.banji.yahewa.ui.theme.White
import com.lawal.banji.yahewa.view.model.CurrentWeatherViewModel
import com.lawal.banji.yahewa.view.model.ForecastViewModel

@Composable
fun ScreenRoutingFloatingActionButton(
    navController: NavController,
    modifier: Modifier = Modifier,
    containerColor: Color = Black,
    contentColor: Color = White
) {
    // Observe the current route dynamically using navController.currentBackStackEntryFlow
    val currentRoute by navController.currentBackStackEntryFlow.collectAsState(initial = null)

    // Determine the current route (null-safe)
    val route = currentRoute?.destination?.route ?: Screens.Current.route

    // Determine the button label based on the current route
    val buttonLabel = when (route) {
        Screens.Forecast.route -> "Forecast"
        Screens.Current.route -> "Current Weather"
        else -> ""
    }

    // Define the FloatingActionButton composable
    FloatingActionButton(
        onClick = {
            if (route == Screens.Current.route) {
                navController.navigate(Screens.Forecast.route)
            } else if (route == Screens.Forecast.route) {
                navController.navigate(Screens.Current.route) {
                    popUpTo(Screens.Current.route) { inclusive = true }
                }
            }
        },
        containerColor = containerColor,
        contentColor = contentColor,
        modifier = modifier
            .padding(8.dp) // Add padding around the FAB
    ) {
        // Set the button label text
        Text(text = buttonLabel, color = White)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenRouter(
    navController: NavHostController,
    currentWeatherViewModel: CurrentWeatherViewModel,
    forecastViewModel: ForecastViewModel,
    startDestination: String = Screens.Current.route
) {

    val currentWeatherState = currentWeatherViewModel.currentWeatherState.collectAsState().value
    val forecastState = forecastViewModel.forecastState.collectAsState().value

    Scaffold(
        bottomBar = {
            ScreenRoutingButtonBar(navController = navController)
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .padding(innerPadding)
                .padding(0.5.dp)
                .background(DefaultDisplayBackgroundColor)
        ) {

            composable(Screens.Current.route) {
                CurrentWeatherScreen(
                    currentWeatherState = currentWeatherState,
                    onNavigate = { itemId ->
                        navController.navigate(Screens.Details.createRoute(itemId.toString()))
                    },
                    onZipcodeEntered = { zipCode ->
                        currentWeatherViewModel.setZipcode(zipCode)
                    }
                )
            }

            composable(Screens.Forecast.route) {
                NewForecastScreen(
                    forecastState = forecastState,
                    onNavigate = { itemId ->
                        navController.navigate(Screens.Details.createRoute(itemId.toString()))
                    },
                    onZipcodeEntered = { zipCode ->
                        forecastViewModel.setZipcode(zipCode)
                    }
                )
            }
        }
    }
}


@Composable
fun ScreenRoutingButtonBar(navController: NavController) {
    BottomAppBar(
        containerColor = Black,
        contentColor = White
    ) {
        // Center FAB within the BottomAppBar
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().padding(0.dp)
        ) {
            ScreenRoutingFloatingActionButton(
                navController = navController,
                modifier = Modifier.align(Alignment.Center).padding(0.dp).fillMaxWidth()
                    .fillMaxHeight()// Center the FAB
            )
        }
    }
}







