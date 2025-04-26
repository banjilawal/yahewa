package com.lawal.banji.yahewa.utils

val locations = setOf(
    Location(name = "Lagos", latitude = 6.465422, longitude = 3.406448),
    Location(name = "San Diego", latitude = 32.7153, longitude = -117.1573),
    Location(name = "Milwaukee", latitude = 43.038902, longitude = -87.906471),
    Location(name = "St. Louis", latitude = 38.627003, longitude = -90.199402),
    Location(name = "St. Paul", latitude = 44.954445, longitude = -93.091301),
    Location(name = "New York", latitude = 40.730610, longitude = -73.935242),
    Location(name = "London", latitude = 51.509865, longitude = -0.118092),
    Location(name = "Tucson", latitude = 32.253460, longitude = -110.911789),
    Location(name = "Chicago", latitude = 41.867, longitude = -87.665),
    Location(name = "Minneapolis", latitude = 44.986656, longitude = -93.258133),
    Location(name = "Seattle", latitude = 47.608013, longitude = -122.335167),
    Location(name = "Phoenix", latitude = 33.4675, longitude = -112.05),
    Location(name = "Boston", latitude = 42.361145, longitude = -71.057083),
    Location(name = "Atlanta", latitude = 33.753746, longitude = -84.386330),
    Location(name = "New Orleans", latitude = 29.951065, longitude = -90.071533),
    Location(name = "Miami", latitude = 25.7617, longitude = -80.1918),
    Location(name = "Anchorage", latitude = 61.2176, longitude = -149.8631)
)

fun randomLocation(): Location {
    return locations.random()
//    val index = (0..locations.size).shuffled().last()
//    println("Index: $index")
//    val location = locations.elementAt(index)
//    println("Location: $location")
//    return location
}