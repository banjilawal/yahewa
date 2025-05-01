package com.lawal.banji.yahewa.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lawal.banji.yahewa.destination.DetailsScreen
import com.lawal.banji.yahewa.destination.HomeScreen
import com.lawal.banji.yahewa.destination.PredictionsScreen
import com.lawal.banji.yahewa.view.model.ForecastViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    forecastViewModel: ForecastViewModel,
    startDestination: String = Screens.Home.route
) {
    // Collect forecast states
    val forecastState = forecastViewModel.forecastState.collectAsState().value
    val predictionGroupState = forecastViewModel.predictionGroupState.collectAsState().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val currentRoute = navController.currentBackStackEntry?.destination?.route
                    // Toggle logic: Navigate to Predictions if on Home, otherwise to Home
                    if (currentRoute == Screens.Home.route) {
                        navController.navigate(Screens.Predictions.route)
                    } else {
                        navController.navigate(Screens.Home.route) {
                            popUpTo(Screens.Home.route) { inclusive = true }
                        }
                    }
                }
            ) {
                // Set Button Text based on Current Route
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                Text(
                    if (currentRoute == Screens.Home.route) "Predictions" else "Home"
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            // HomeScreen
            composable(Screens.Home.route) {
                HomeScreen(
                    forecastState = forecastState,
                    onNavigate = { itemId ->
                        navController.navigate(Screens.Details.createRoute(itemId.toString()))
                    },
                    onZipcodeEntered = { zipcode -> forecastViewModel.setZipcode(zipcode) }
                )
            }

            // DetailsScreen
            composable(
                route = Screens.Details.route,
                arguments = listOf(
                    navArgument("itemId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId")
                if (itemId == null) {
                    navController.popBackStack() // Handle missing itemId gracefully
                    return@composable
                }
                DetailsScreen(
                    forecastState = forecastState,
                    itemId = itemId,
                    onZipcodeEntered = { zipcode -> forecastViewModel.setZipcode(zipcode) }
                )
            }

            // PredictionsScreen
            composable(Screens.Predictions.route) {
                PredictionsScreen(
                    predictionGroupState = predictionGroupState,
                    onNavigate = {
                        // Direct navigation handling
                        navController.popBackStack()
                    },
                    onZipcodeEntered = { zipcode -> forecastViewModel.setZipcode(zipcode) } // Add this line
                )
            }

        }
    }
}




