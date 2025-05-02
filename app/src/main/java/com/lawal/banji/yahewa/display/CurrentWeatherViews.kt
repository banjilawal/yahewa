package com.lawal.banji.yahewa.display

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.model.CurrentWeather
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.LargeCornerRadius
import com.lawal.banji.yahewa.ui.theme.LargestIconSize
import com.lawal.banji.yahewa.ui.theme.PowderBlue
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray
import com.lawal.banji.yahewa.ui.theme.SandLighter
import com.lawal.banji.yahewa.ui.theme.SandLightest
import com.lawal.banji.yahewa.ui.theme.Silver
import com.lawal.banji.yahewa.utils.WeatherIcon
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentWeatherView(currentWeather: CurrentWeather) {

    val city = currentWeather.city
    val state = currentWeather.state
    val country = currentWeather.sys.country

    val temperatureFeelsLike = "Feels like ${currentWeather.main.temperatureFeelsLike}°"

    val humidity = "Humidity ${currentWeather.main.percentHumidity} %"
    val pressure = "Pressure ${currentWeather.main.pressure} hPa"

    val iconId = currentWeather.weather[0].iconId
    val description = currentWeather.weather[0].description
    val weather = currentWeather.weather[0].description

    val cityInformation = if (state != null) "$city, $state" else "$city, $country"

    val currentTemperature = "${currentWeather.main.temperature}°"
    val temperatureRange = "Hi ${currentWeather.main.highTemperature}° " +
            "/ ${currentWeather.main.lowTemperature}° Lo"

    val dateTimeString = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("hh:mm a EEEE, dd MMMM yyyy", Locale.getDefault()))

    LazyColumn(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxSize()
            .background(PowderBlueGray, RoundedCornerShape(DefaultCornerRadius))
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Ensure the Text spans the full width
                    .fillMaxHeight(0.16f)
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .background(
                        SandLighter,
                        RoundedCornerShape(DefaultCornerRadius)
                    )
                    .clip(RoundedCornerShape(DefaultCornerRadius))
            ){
                Column() {
                    Text(
                        text = cityInformation,
                        textAlign = TextAlign.Center, // Center-align the text horizontally
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth() // Ensure the Text spans the full width
                            .fillMaxHeight(0.8f)
                            .padding(
                                horizontal = 5.dp,
                                vertical = 5.dp
                            ) // Add the needed 5px horizontal and vertical padding
                            .background(
                                SandLighter,
                                RoundedCornerShape(DefaultCornerRadius)
                            ) // Background color for the Text
                            .clip(RoundedCornerShape(DefaultCornerRadius)) // Rounded corners for the background
                    )
                    Text(
                        text = dateTimeString, // Dynamically fetch formatted time
                        textAlign = TextAlign.Center, // Center-align the text horizontally
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth() // Ensure the Text spans the full width
                            .fillMaxHeight(0.2f)
                            .padding(
                                horizontal = 5.dp,
                                vertical = 0.dp
                            ) // Add the needed 5px horizontal and vertical padding
                            .background(
                                SandLightest,
                                RoundedCornerShape(DefaultCornerRadius)
                            ) // Background color for the Text
                            .clip(RoundedCornerShape(DefaultCornerRadius)) // Rounded corners for the background
                    )
                }

            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillParentMaxHeight(0.75f) // Adjust height as needed
                    .padding(0.dp)
                    .background(Silver) // Background for the icon
                    .clip(RoundedCornerShape(LargeCornerRadius)) // Rounded corners for the entire Box
            ) {
                // Weather Icon
                WeatherIcon(
                    iconId = iconId,
                    contentDescription = description,
                    iconSize = LargestIconSize,
                    modifier = Modifier
                        .fillMaxSize() // Ensure the WeatherIcon fills the entire Box
                )

                // Current Temperature Text (Top-Center)
                Text(
                    text = currentTemperature,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter) // Align to the top center
                        .padding(top = 8.dp) // Small padding to avoid sticking to the top edge
                        .background(
                            PowderBlue.copy(alpha = 0.0f), // Transparent overlay background
                            RoundedCornerShape(DefaultCornerRadius)
                        ) // Rounded corners for the Text
                        .clip(RoundedCornerShape(DefaultCornerRadius)) // Apply rounded corners
                )

                // Weather Description Text (Bottom-Center)
                Text(
                    text = weather,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter) // Align to the bottom center
                        .padding(bottom = 8.dp) // Small padding to avoid sticking to the bottom edge
                        .background(
                            PowderBlue.copy(alpha = 0.0f), // Transparent overlay background
                            RoundedCornerShape(DefaultCornerRadius)
                        ) // Rounded corners for the Text
                        .clip(RoundedCornerShape(DefaultCornerRadius)) // Apply rounded corners
                )
            }
        }
        item {
            Text(
                text = temperatureRange,
                textAlign = TextAlign.Center, // Center-align the text horizontally
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth() // Ensure the Text spans the full width
                    .fillMaxHeight(0.25f)
                    .padding(
                        horizontal = 32.dp,
                        vertical = 5.dp
                    ) // Add the needed 5px horizontal and vertical padding
                    .background(
                        Silver,
                        RoundedCornerShape(DefaultCornerRadius)
                    ) // Background color for the Text
                    .clip(RoundedCornerShape(LargeCornerRadius)) // Rounded corners for the background
            )
        }
    }
}


//@Composable
//fun CurrentWeatherView(currentWeather: CurrentWeather) {
//    val city = currentWeather.city
//    val state = currentWeather.state
//    val country = currentWeather.sys.country
//    val currentTemperature = "${currentWeather.main.temperature}°"
//    val temperatureFeelsLike = "Feels like ${currentWeather.main.temperatureFeelsLike}°"
//    val lowTemperature = "Low ${currentWeather.main.lowTemperature}°"
//    val highTemperature = "High ${currentWeather.main.highTemperature}°"
//    val humidity = "Humidity ${currentWeather.main.percentHumidity} %"
//    val pressure = "Pressure ${currentWeather.main.pressure} hPa"

//    val iconId = currentWeather.weather[0].iconId
//    val description = currentWeather.weather[0].description
//    val weather = currentWeather.weather[0]
//
//    val cityInformation = if (state != null) "$city, $state" else "$city, $country"
//    val weatherInformation = "Hi ${currentWeather.main.highTemperature}° " +
//            "/ Lo ${currentWeather.main.lowTemperature}° $description"
//
//    LazyColumn(
//        modifier = Modifier
//            .padding(DefaultPadding)
//            .fillMaxSize()
//            .background(LightGray1, RoundedCornerShape(DefaultCornerRadius))
//    ) {
        // Row containing City
//        item {
//            // City needs to be centered. Fills the width of it's center
//            Text(
//                text = cityInformation,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(DefaultHeadingColor)
//                    .padding(horizontal = LargerPadding, vertical = SmallPadding)
//                    .clip(RoundedCornerShape(DefaultCornerRadius)),
//                textAlign = TextAlign.Center
//            )
//        }
        // Row containing the temperature and weather icon
//        item {
//            androidx.compose.foundation.layout.Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 0.dp, bottom = 0.dp)
//                    .background(LightGray2),
//                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
//            ) {
//                // Column for temperature data
//                androidx.compose.foundation.layout.Column(
//                    modifier = Modifier
//                        .weight(0.6f) // Reduce weight so the column doesn't use too much space
//                        .padding(SmallPadding)
//                ) {
//                    Text(
//                        text = currentTemperature,
//                        style = MaterialTheme.typography.titleMedium,
//                        textAlign = TextAlign.Center, // Align to start for compact content
//                        modifier = Modifier.fillMaxWidth().padding(bottom = DefaultPadding)
//                    )
//                    Text(
//                        text = temperatureFeelsLike,
//                        style = MaterialTheme.typography.bodySmall, // Keep font size consistent
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.fillMaxWidth().padding(bottom = DefaultPadding)
//                    )
//                }

            // Weather icon
//            Box(
//                modifier = Modifier
//                            .weight(0.4f)
//                    .padding(SmallPadding)
//                        .align(CenterVertically)
//            ) {
//                WeatherIcon(iconId, backgroundColor = White, iconSize = LargeIconSize, modifier = Modifier)
//                    .padding(SmallPadding)
//                    .background(White, RoundedCornerShape(DefaultCornerRadius))
//                    .fillMaxSize()
//                )
//            }
//            WeatherIcon(
//                iconId = iconId,
//                contentDescription = description,
//                iconSize = LargestIconSize,
//                modifier = Modifier
//                    .padding(DefaultPadding)
//                    .fillMaxSize()
//                    .background(Silver)
//                    .clip(RoundedCornerShape(DefaultCornerRadius))
//
//
//            )
//        }
//    }
//}
//                }
//            }
//        item {
//            Text(
//                text = lowTemperature,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(SmallerPadding)
//            )
//        }
//        item {
//            Text(
//                text = highTemperature,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(SmallerPadding)
//            )
//        }
//        item {
//            Text(
//                text = humidity,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(SmallerPadding)
//            )
//        }
//        item {
//            Text(
//                text = pressure,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(SmallerPadding)
//            )
//        }
//        }
//    }
//}

//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun ForecastDetailsView(currentWeather: CurrentWeather) {
//
//    val city = currentWeather.city
//    val state = currentWeather.state
//    val country = currentWeather.sys.country
//
//    val description = currentWeather.weather.getOrNull(0)?.description ?: "No description available"
//
//    val latitude = currentWeather.coordinates?.latitude ?: "Unknown Latitude"
//    val longitude = currentWeather.coordinates?.longitude ?: "Unknown Longitude"
//
//    val windSpeed = currentWeather.wind.speed ?: "Unknown speed"
//    val windDirection = currentWeather.wind.direction ?: "Unknown direction"
//    val windGust = currentWeather.wind.gust ?: "Unknown gust"
//    val percentCloudiness = "${currentWeather.clouds?.all ?: "N/A"} % cloudiness"
//    val visibility = currentWeather.visibility?.toString() ?: "N/A visibility"
//    val sunrise = "sunrise: ${currentWeather.sys.sunrise}" //?.let { "$it sunrise" } ?: "No sunrise data"
//    val sunset = "sunset: ${currentWeather.sys.sunset}"  //?.let { "$it sunset" } ?: "No sunset data"
//
//    val location = if (state != null) "$city, $state" else "$city, $country"
//    val heading  = "$location Weather Details"
//    val coordinates = "latitude: ${currentWeather.coordinates.latitude} longitude  ${currentWeather.coordinates.longitude}"
//
//    LazyColumn(
//        modifier = Modifier
//            .padding(DefaultPadding)
//            .fillMaxSize()
//            .background(PowderBlueGray)
//    ) {
//        item {
            // City needs to be centered. Fills the width of it's center
//            Text(
//                text = heading,
//                style = MaterialTheme.typography.titleMedium,
//                modifier = Modifier.fillMaxWidth()
//                    .padding(DefaultPadding).
//                    background(DefaultHeadingColor),
//                textAlign = TextAlign.Center
//            )
//        }
//        item {
//            // City needs to be centered. Fills the width of it's center
//            Text(
//                text = coordinates,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.fillMaxWidth()
//                    .padding(DefaultPadding).
//                    background(DefaultBoxColor),
//                textAlign = TextAlign.Start
//            )
//        }
//        item {
//            // City needs to be centered. Fills the width of it's center
//            Text(
//                text = "$sunrise $sunset",
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.fillMaxWidth()
//                    .padding(DefaultPadding).
//                    background(SandLighter),
//                textAlign = TextAlign.Start
//            )
//        }
//        item {
//            Text(
//                text = percentCloudiness,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(SmallerPadding))
//        }
//        item {
//            Text(
//                text = visibility,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(SmallerPadding))
//        }
//        item {
////            Text(
////                text = description,
////                style = MaterialTheme.typography.bodyMedium,
////                modifier = Modifier.padding(SmallerPadding))
////        }
//}
//}