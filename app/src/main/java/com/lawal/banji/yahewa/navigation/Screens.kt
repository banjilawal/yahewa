package com.lawal.banji.yahewa.navigation

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Current: Screens("currentWeather")
    object Forecast: Screens("newForecast")
    object Details : Screens("details/{itemId}") { // Declare itemId directly in the base route
        fun createRoute(itemId: String) = "details/$itemId" // Dynamically provide the value for itemId
    }
    object Forecasts : Screens("forecastRecords") // Add Forecasts route
}


