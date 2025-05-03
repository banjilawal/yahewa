package com.lawal.banji.yahewa.utils

import com.lawal.banji.yahewa.model.City

val cities = setOf(
    City(name = "Lagos", latitude = 6.465422, longitude = 3.406448),
    City(name = "San Diego", latitude = 32.7153, longitude = -117.1573),
    City(name = "Milwaukee", latitude = 43.038902, longitude = -87.906471),
    City(name = "St. Louis", latitude = 38.627003, longitude = -90.199402),
    City(name = "St. Paul", latitude = 44.954445, longitude = -93.091301),
    City(name = "New York", latitude = 40.730610, longitude = -73.935242),
    City(name = "London", latitude = 51.509865, longitude = -0.118092),
    City(name = "Tucson", latitude = 32.253460, longitude = -110.911789),
    City(name = "Chicago", latitude = 41.867, longitude = -87.665),
    City(name = "Minneapolis", latitude = 44.986656, longitude = -93.258133),
    City(name = "Seattle", latitude = 47.608013, longitude = -122.335167),
    City(name = "Phoenix", latitude = 33.4675, longitude = -112.05),
    City(name = "Boston", latitude = 42.361145, longitude = -71.057083),
    City(name = "Atlanta", latitude = 33.753746, longitude = -84.386330),
    City(name = "New Orleans", latitude = 29.951065, longitude = -90.071533),
    City(name = "Miami", latitude = 25.7617, longitude = -80.1918),
    City(name = "Anchorage", latitude = 61.2176, longitude = -149.8631)
)

fun getRandomCity(): City {
    return cities.random()
}