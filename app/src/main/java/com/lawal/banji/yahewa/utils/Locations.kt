package com.lawal.banji.yahewa.utils

val locations = listOf(
    Location("Lagos", 3.3792, 6.5244),
    Location("San Diego", 32.715736, -117.161087),
    Location("Milwaukee", 43.038902, -87.906471),
    Location("St. Louis", 38.6270, -90.1994),
    Location("St. Paul", 44.9537, -93.0900),
    Location("New York", 40.7128, -74.0060),
    Location("London", 51.5074, -0.1278),
    Location("Tucson", 32.22174, -110.92648),
    Location("Chicago", 41.8781, -87.6298),
    Location("Minneapolis", 44.9778, -93.2650),
    Location("Seattle", 47.6062, -122.3321),
    Location("Phoenix", 33.4484, -112.0740),
    Location("Boston", 42.3601, -71.0589),
    Location("Atlanta", 33.7490, -84.3880),
    Location("New Orleans", 29.9511, -90.0715),
    Location("Miami", 25.7617, -80.1918),
    Location("Anchorage", 61.2176, -149.8631)
)

fun randomLocation(): Location {
    return locations.random()
}