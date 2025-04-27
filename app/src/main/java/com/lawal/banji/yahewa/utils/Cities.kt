package com.lawal.banji.yahewa.utils

import com.lawal.banji.yahewa.model.City
import com.lawal.banji.yahewa.model.Coordinates

val cities = setOf(
    City(name = "Lagos", coordinates= Coordinates(latitude = 6.465422, longitude = 3.406448)),
    City(name = "San Diego", coordinates = Coordinates(latitude =32.7153, longitude = -117.1573)),
    City(name = "Milwaukee", coordinates = Coordinates(latitude =43.038902, longitude = -87.906471)),
    City(name = "St. Louis", coordinates = Coordinates(latitude =38.627003, longitude = -90.199402)),
    City(name = "St. Paul", coordinates = Coordinates(latitude =44.954445, longitude = -93.091301)),
    City(name = "New York", coordinates = Coordinates(latitude =40.730610, longitude = -73.935242)),
    City(name = "London", coordinates = Coordinates(latitude =51.509865, longitude = -0.118092)),
    City(name = "Tucson", coordinates = Coordinates(latitude =32.253460, longitude = -110.911789)),
    City(name = "Chicago", coordinates = Coordinates(latitude =41.867, longitude = -87.665)),
    City(name = "Minneapolis", coordinates = Coordinates(latitude =44.986656, longitude = -93.258133)),
    City(name = "Seattle", coordinates = Coordinates(latitude =47.608013, longitude = -122.335167)),
    City(name = "Phoenix", coordinates = Coordinates(latitude =33.4675, longitude = -112.05)),
    City(name = "Boston", coordinates = Coordinates(latitude =42.361145, longitude = -71.057083)),
    City(name = "Atlanta", coordinates = Coordinates(latitude =33.753746, longitude = -84.386330)),
    City(name = "New Orleans", coordinates = Coordinates(latitude =29.951065, longitude = -90.071533)),
    City(name = "Miami", coordinates = Coordinates(latitude =25.7617, longitude = -80.1918)),
    City(name = "Anchorage", coordinates = Coordinates(latitude =61.2176, longitude = -149.8631)),
    City(name = "Denver", coordinates = Coordinates(latitude =39.7392, longitude = -104.9903)),
    City(name = "Dallas", coordinates = Coordinates(latitude =32.7767, longitude = -96.7970)),
    City(name = "Houston", coordinates = Coordinates(latitude =29.7604, longitude = -95.3698)),
    City(name = "San Francisco", coordinates = Coordinates(latitude =37.7749, longitude = -122.4194)),
    City(name = "Detroit", coordinates = Coordinates(latitude =42.3314, longitude = -83.0458)),
    City(name = "Baltimore", coordinates = Coordinates(latitude =39.2904, longitude = -76.6122)),
    City(name = "Philadelphia", coordinates = Coordinates(latitude =39.9526, longitude = -75.1652)),
    City(name = "Washington", coordinates = Coordinates(latitude =38.9072, longitude = -77.0369)),
    City(name = "Charlotte", coordinates = Coordinates(latitude =35.2271, longitude = -80.8431)),
    City(name = "Cleveland", coordinates = Coordinates(latitude =41.4995, longitude = -81.6954)),
    City(name = "Orlando", coordinates = Coordinates(latitude =28.5383, longitude = -81.3792)),
    City(name = "Kansas City", coordinates = Coordinates(latitude =39.0997, longitude = -94.5786)),
    City(name = "Salt Lake City", coordinates = Coordinates(latitude =40.7608, longitude = -111.8910)),
    City(name = "Nashville", coordinates = Coordinates(latitude =36.1627, longitude = -86.7816)),
    City(name = "Columbus", coordinates = Coordinates(latitude =39.9612, longitude = -82.9988)),
    City(name = "Pittsburgh", coordinates = Coordinates(latitude =40.4406, longitude = -79.9959)),
    City(name = "Oklahoma City", coordinates = Coordinates(latitude =35.4444, longitude = -97.5309)),
    City(name = "Portland", coordinates = Coordinates(latitude =45.5200, longitude = -122.6850)),
    City(name = "Las Vegas", coordinates = Coordinates(latitude =36.1667, longitude = -115.1333)),
    City(name = "Duluth", coordinates = Coordinates(latitude =46.7867, longitude = -92.1005))
)

fun getRandomCity(): City {
    return cities.random()
}