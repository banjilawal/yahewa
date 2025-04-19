package com.lawal.banji.yahewa.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.lawal.banji.yahewa.R

@Composable
fun WeatherIconFromApiId(
    weatherApiId: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    val iconResId = when (weatherApiId.lowercase()) {
        "01d" -> R.drawable.sunny              // Clear sky (day)
        "01n" -> R.drawable.moon               // Clear sky (night)
        "02d" -> R.drawable.clouds_and_sun     // Few clouds (day)
        "02n" -> R.drawable.cloudy_night       // Few clouds (night)
        "03d", "03n" -> R.drawable.clouds      // Scattered clouds
        "04d", "04n" -> R.drawable.clouds_1    // Broken clouds
        "09d", "09n" -> R.drawable.raindrops   // Shower rain
        "10d" -> R.drawable.morning_rain       // Rain (day)
        "10n" -> R.drawable.raining            // Rain (night)
        "11d", "11n" -> R.drawable.bolt        // Thunderstorm
        "13d" -> R.drawable.morning_snow       // Snow (day)
        "13n" -> R.drawable.snowy              // Snow (night)
        "50d", "50n" -> R.drawable.calm        // Mist / fog

        // Bonus or unused icons — still supported
        "bolt" -> R.drawable.bolt
        "calm" -> R.drawable.calm
        "celsius" -> R.drawable.celsius
        "clouds" -> R.drawable.clouds
        "clouds_1" -> R.drawable.clouds_1
        "clouds_and_sun" -> R.drawable.clouds_and_sun
        "cloudy_night" -> R.drawable.cloudy_night
        "eclipse" -> R.drawable.eclipse
        "farenheit" -> R.drawable.farenheit
        "hail" -> R.drawable.hail
        "icicle" -> R.drawable.icicle
        "moon" -> R.drawable.moon
        "moon_1" -> R.drawable.moon_1
        "morning_rain" -> R.drawable.morning_rain
        "morning_snow" -> R.drawable.morning_snow
        "raindrops" -> R.drawable.raindrops
        "raining" -> R.drawable.raining
        "rainy" -> R.drawable.rainy
        "snow" -> R.drawable.snow
        "snowflake" -> R.drawable.snowflake
        "snowing_1" -> R.drawable.snowing_1
        "snowy" -> R.drawable.snowy
        "sunny" -> R.drawable.sunny

        else -> R.drawable.calm // Fallback
    }

    Image(
        painter = painterResource(id = iconResId),
        contentDescription = contentDescription ?: weatherApiId,
        modifier = modifier
    )
}