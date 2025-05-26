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
import com.lawal.banji.yahewa.destination.ForecastScreen
import com.lawal.banji.yahewa.destination.HomeScreen
import com.lawal.banji.yahewa.ui.theme.Black
import com.lawal.banji.yahewa.ui.theme.DefaultDisplayBackgroundColor
import com.lawal.banji.yahewa.ui.theme.White
import com.lawal.banji.yahewa.view.model.CurrentWeatherViewModel
import com.lawal.banji.yahewa.view.model.ForecastViewModel

@Composable
fun RoutingFloatingButtonBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    containerColor: Color = Black,
    contentColor: Color = White
) {
    // Observe the current route dynamically using navController.currentBackStackEntryFlow
    val currentRoute by navController.currentBackStackEntryFlow.collectAsState(initial = null)

    // Determine the current route (null-safe)
    val route = currentRoute?.destination?.route ?: Screens.Home.route

    // Determine the button label based on the current route
    val buttonLabel = when (route) {
        Screens.Home.route -> "Get Forecasts"
        Screens.Forecasts.route -> "Get Current Weather"
        else -> ""
    }

    // Define the FloatingActionButton composable
    FloatingActionButton(
        onClick = {
            // Handle button click based on current route
            if (route == Screens.Home.route) {
                navController.navigate(Screens.Forecasts.route)
            } else if (route == Screens.Forecasts.route) {
                navController.navigate(Screens.Home.route) {
                    popUpTo(Screens.Home.route) { inclusive = true }
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
    startDestination: String = Screens.Home.route
) {

    val currentWeatherState = currentWeatherViewModel.currentWeatherState.collectAsState().value
    val forecastState = forecastViewModel.forecastState.collectAsState().value

    Scaffold(
        bottomBar = {
            RoutingButtonBar(navController = navController)
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
            // HomeScreen
            composable(Screens.Home.route) {
                HomeScreen(
                    currentWeatherState = currentWeatherState,
                    onNavigate = { itemId ->
                        navController.navigate(Screens.Details.createRoute(itemId.toString()))
                    },
                    onZipcodeEntered = { zipcode ->
                        currentWeatherViewModel.setZipcode(zipcode)
                    }
                )
            }

            // ForecastsScreen
            composable(Screens.Forecasts.route) {
                ForecastScreen(
                    forecastState = forecastState,
                    onNavigate = {
                        // Direct navigation handling
                        navController.popBackStack()
                    },
                    onZipcodeEntered = { zipcode ->
                        forecastViewModel.setZipcode(zipcode)
                    }
                )
            }
        }
    }
}

@Composable
fun RoutingButtonBar(navController: NavController) {
    BottomAppBar(
        containerColor = Black,
        contentColor = White
    ) {
        // Center FAB within the BottomAppBar
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().padding(0.dp)
        ) {
            FloatingActionButton(
                navController = navController,
                modifier = Modifier.align(Alignment.Center).padding(0.dp).fillMaxWidth().fillMaxHeight()// Center the FAB
            )
        }
    }
}







