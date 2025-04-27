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
    // Collect forecast state once, at the top level
    val forecastState = forecastViewModel.forecastState.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // HomeScreen Composable
        composable(Screens.Home.route) {
            HomeScreen(
                forecastState = forecastState,
                onNavigate = { itemId ->
                    itemId?.let {
                        navController.navigate(Screens.Details.createRoute(it.toString()))
                    } ?: navController.popBackStack() // Handle null gracefully
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
            val itemId = backStackEntry.arguments?.getString("itemId")
            if (itemId == null) {
                // Handle missing itemId gracefully
                navController.popBackStack()
                return@composable
            }

            DetailsScreen(
                forecastState = forecastState,
                itemId = itemId, // Pass the itemId argument
                onZipcodeEntered = { zipcode -> forecastViewModel.setZipcode(zipcode) }
            )
        }
    }
}


