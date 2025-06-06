package com.lawal.banji.yahewa.utils

object AppDefault {
    const val TEMPERATURE: Double = 0.0
    const val PRESSURE: Double = 0.0
    const val HUMIDITY: Double = 0.0
    const val PRECIPITATION:Double = 0.0
    const val WIND_SPEED: Double = 0.0
    const val WEATHER_ICON: String = "01d"
    const val REGION: String = "MN"
    const val COUNTRY: String = "US"
    const val ZIPCODE : String = "55404"
    const val CITY_NAME: String = "Minneapolis"
    const val LATITUDE: Double = 44.986656
    const val LONGITUDE: Double = -93.258133
    const val DEBOUNCE_MILLISECONDS: Long = 500L
    const val API_KEY: String = "43d92973340fa3166680bbe3af8d3943"
    const val NUMBER_OF_FORECASTS: Int = 16
    const val NUMBER_OF_GEOCODE_RESULTS: Int = 1
    const val DEFAULT_MEASUREMENT_SYSTEM: String = "imperial"
    const val DATA_FETCH_FAILURE_MESSAGE: String = "Failed ot fetch weather data."
    const val DATA_FETCH_SUCCESS_MESSAGE: String = "Weather data fetched successfully."
    const val DATA_NOT_FOUND_MESSAGE: String = "Weather data not found."

    const val CACHE_LIFETIME_MILLISECONDS: Long = 5 * 60 * 1000L
    const val MILLISECONDS_PER_SECOND: Long = 1000L

// Permission Request Codes
    const val COARSE_LOCATION_REQUEST_CODE: Int = 100
    const val FINE_LOCATION_REQUEST_CODE: Int = 101
    const val FOREGROUND_SERVICE_PERMISSION_REQUEST_CODE = 102
    const val NOTIFICATION_PERMISSION_REQUEST_CODE = 103
}