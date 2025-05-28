package com.lawal.banji.yahewa.navigation

sealed class Screens(val route: String) {
    object Current: Screens("currentWeather")
    object Forecast: Screens("newForecast")
}


