package com.lawal.banji.yahewa.utils

import com.lawal.banji.yahewa.model.GeoLocation

val locations = setOf(
    GeoLocation(name = "Lagos", latitude = 6.465422, longitude = 3.406448),
    GeoLocation(name = "San Diego", latitude = 32.7153, longitude = -117.1573),
    GeoLocation(name = "Milwaukee", latitude = 43.038902, longitude = -87.906471),
    GeoLocation(name = "St. Louis", latitude = 38.627003, longitude = -90.199402),
    GeoLocation(name = "St. Paul", latitude = 44.954445, longitude = -93.091301),
    GeoLocation(name = "New York", latitude = 40.730610, longitude = -73.935242),
    GeoLocation(name = "London", latitude = 51.509865, longitude = -0.118092),
    GeoLocation(name = "Tucson", latitude = 32.253460, longitude = -110.911789),
    GeoLocation(name = "Chicago", latitude = 41.867, longitude = -87.665),
    GeoLocation(name = "Minneapolis", latitude = 44.986656, longitude = -93.258133),
    GeoLocation(name = "Seattle", latitude = 47.608013, longitude = -122.335167),
    GeoLocation(name = "Phoenix", latitude = 33.4675, longitude = -112.05),
    GeoLocation(name = "Boston", latitude = 42.361145, longitude = -71.057083),
    GeoLocation(name = "Atlanta", latitude = 33.753746, longitude = -84.386330),
    GeoLocation(name = "New Orleans", latitude = 29.951065, longitude = -90.071533),
    GeoLocation(name = "Miami", latitude = 25.7617, longitude = -80.1918),
    GeoLocation(name = "Anchorage", latitude = 61.2176, longitude = -149.8631)
)

fun randomGeoLocation(): GeoLocation {
    return locations.random()
}