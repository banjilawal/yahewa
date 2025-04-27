package com.lawal.banji.yahewa.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lawal.banji.yahewa.destination.DetailsScreen
import com.lawal.banji.yahewa.destination.HomeScreen
import com.lawal.banji.yahewa.view.model.ForecastViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    forecastViewModel: ForecastViewModel,
    startDestination: String = Screens.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // HomeScreen Composable
        composable(Screens.Home.route) {
            HomeScreen(
                forecastState = forecastViewModel.forecastState.collectAsState().value,
                onNavigate = { itemId ->
                    if (itemId != null) {
                        // Navigate to DetailsScreen with a valid itemId
                        navController.navigate(Screens.Details.createRoute(itemId.toString()))
                    } else {
                        // Handle invalid itemId (log error or show Snackbar)
                        throw IllegalArgumentException("itemId cannot be null or blank.")
                    }
                },
                onZipcodeEntered = { zipcode -> forecastViewModel.setZipcode(zipcode) }
            )
        }

        // DetailsScreen Composable
        composable(
            route = Screens.Details.route,
            arguments = listOf(
                navArgument("itemId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Safely retrieve the itemId argument
            val itemId = backStackEntry.arguments?.getString("itemId")
                ?: throw IllegalArgumentException("Missing 'itemId' argument in route.")

            DetailsScreen(
                forecastState = forecastViewModel.forecastState.collectAsState().value,
                onNavigate = {},
                onZipcodeEntered = { zipcode -> forecastViewModel.setZipcode(zipcode) }
            )
        }
    }
}

