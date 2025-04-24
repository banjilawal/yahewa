package com.lawal.banji.yahewa.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultDisplayBackgroundColor
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.weather.model.WeatherRecord

@Composable
fun CurrentWeatherDisplay(
    weatherRecord: WeatherRecord?,
    padding: Dp = DefaultPadding,
    cornerRadius: Dp = DefaultCornerRadius,
    backgroundColor: Color = DefaultDisplayBackgroundColor,
    modifier: Modifier = Modifier
) {
    // Safely handle null `weatherRecord`
    if (weatherRecord == null) {
        Text(
            text = "Weather data is not available",
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        )
        return
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding)
            .statusBarsPadding()
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
    ) {
        // Location Display
        LocationBox(city = weatherRecord.city)

        // Temperature and Icon (Safe access for list)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TemperatureBox(temperature = weatherRecord.main.temperature)

            // Safely display icon only if `weather` list is not empty
            val iconId = weatherRecord.weather.getOrNull(0)?.iconId
            if (iconId != null) {
                IconBox(iconId = iconId)
            }
        }

        // Feels Like Temperature
//        TemperatureBox(
//            temperature = weatherRecord.current.temperatureFeelsLike,
//            information = "Feels Like:"
//        )

        // High and Low Temperatures
//        Column(
//            modifier = Modifier.fillMaxWidth(),
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
//            TemperatureBox(temperature = weatherRecord.main.lowTemperature, information = "Low:")
//            TemperatureBox(temperature = weatherRecord.main.highTemperature, information = "High:")
//        }

        // Humidity and Pressure Information
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
                HumidityBox(percentHumidity = weatherRecord.main.percentHumidity)
                PressureBox(pressure = weatherRecord.main.pressure)
        }
    }
}




//@Composable
//fun CurrentWeatherDisplay(
//    weatherRecord : WeatherRecord,
//    cornerRadius: Dp = DefaultCornerRadius,
//    displayBackgroundColor: Color = DefaultDisplayBackgroundColor,
//    displayPadding : Dp = DefaultPadding,
//    modifier: Modifier = Modifier .fillMaxSize()
//        .padding(horizontal = 0.dp)
//        .statusBarsPadding()
//        .clip(RoundedCornerShape(LargeCornerRadius)
//) {
//            Column(
//                modifier = Modifier .fillMaxWidth()
//                    .padding(horizontal = 0.dp)
//                    .statusBarsPadding()
//                    .clip(RoundedCornerShape(LargeCornerRadius) )
//                    .background(PowderBlueGray)// Adds padding equal to the height of the status bar
//            ) {
//                WeatherRecordDisplay(
//                    weatherRecord = weatherRecord,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(displayPadding)
//                        .background(displayBackgroundColor)
//                        .clip(RoundedCornerShape(cornerRadius))
//                )
//            }
//}