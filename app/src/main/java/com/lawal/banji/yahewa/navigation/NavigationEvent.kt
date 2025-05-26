package com.lawal.banji.yahewa.navigation

sealed class NavigationEvent {
    data class ToHourly(val hourly: String) : NavigationEvent()
    object ToProfile : NavigationEvent()
}
