package com.lawal.banji.yahewa.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import com.lawal.banji.yahewa.R
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.LargeCornerRadius
import com.lawal.banji.yahewa.ui.theme.LargePadding
import com.lawal.banji.yahewa.ui.theme.LargerPadding
import com.lawal.banji.yahewa.ui.theme.LightGray1
import com.lawal.banji.yahewa.ui.theme.LightGray2
import com.lawal.banji.yahewa.ui.theme.PowderBlue
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray
import com.lawal.banji.yahewa.ui.theme.SandLightest
import com.lawal.banji.yahewa.ui.theme.Silver
import com.lawal.banji.yahewa.ui.theme.SmallPadding
import com.lawal.banji.yahewa.ui.theme.SmallerPadding
import com.lawal.banji.yahewa.utils.CustomBox
import com.lawal.banji.yahewa.utils.CustomRow
import com.lawal.banji.yahewa.utils.CustomText
import com.lawal.banji.yahewa.utils.WeatherIconFromApiId
import com.lawal.banji.yahewa.utils.customBorder

import com.lawal.banji.yahewa.weather.model.WeatherRecord

private val commonBoxModifier = Modifier
    .fillMaxWidth()
    .padding(8.dp)

@Composable
fun WeatherDetailsDisplay(weatherRecord: WeatherRecord) {

    Column(
        modifier = Modifier .fillMaxWidth()
                .padding(horizontal = 0.dp)
                .statusBarsPadding()
                .clip(RoundedCornerShape(LargeCornerRadius) )
                .background(PowderBlueGray)// Adds padding equal to the height of the status bar
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DefaultPadding)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart) // Align to the top-right corner
                    .background(
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(SmallPadding)
                    )
                    .padding(horizontal = DefaultPadding, vertical = SmallerPadding)
            ) {
                Text(
                    text =  stringResource(id = R.string.app_name),
                    color = Silver,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        /*
        * Node Type: CustomBox
         * Description: Displays the name of the city or location.
         * Color: PowderBlue
         * Modifier: weight(0.75f)
         * Text Style: headlineLarge
         * Text Alignment: Center
         *
         * Child Node Type: CustomText
        * */
        CustomBox(color = PowderBlue, modifier = Modifier.weight(0.75f)) {
            CustomText(
                content = weatherRecord.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
        }

        /*
        * Node Type: CustomBox
         * Description: Displays the weather information.
         * Color: Silver
         * Modifier: weight(1.6f)
         * Text Style: headlineMedium
         * Text Alignment: Start
         * Weather Icon: Displays the weather icon based on the API ID.
         * Feels Like: Displays the "Feels Like" temperature.
         *
         * Child Node Type: Column
        * */
        CustomBox(color = Silver, modifier = Modifier.weight(1.6f)) {

            /*
             The column has two rows. First row contains current temperature and weather icon. The
             second row contains the "Feels Like" temperature.
             */
            Column(modifier = Modifier.fillMaxWidth().padding(DefaultPadding).weight(1.8f)) {
                CustomRow(modifier = Modifier.customBorder()) {
                    CustomText(
                        content = "${weatherRecord.main.temperature}°",
                        modifier = Modifier.weight(1f)
                            .clip(RoundedCornerShape(DefaultCornerRadius)), // Add rounded cor
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    WeatherIconFromApiId(
                        weatherApiId = weatherRecord.weather[0].iconId,
                        modifier = Modifier.size(178.dp)
                            .clip(RoundedCornerShape(DefaultCornerRadius)) // Add rounded corners
                            .background(color = LightGray)
                            .padding(DefaultPadding)
                            .then(Modifier.align(Alignment.CenterVertically)),
                        contentDescription = weatherRecord.weather[0].description
                    )
                }
//                Row(
//                    modifier = Modifier.fillMaxWidth()
//                        .weight(1.8f)
//                        .background(Silver)
//                        .padding(1.dp)
//                        .clip(RoundedCornerShape(DefaultCornerRadius) )
//                        .border(
//                            width = 2.dp,
//                            color = LightGray1,
//                            shape = RoundedCornerShape(DefaultCornerRadius)
//                        ),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    // Temperature on the left
//                    CustomText(
//                        content = "${weatherRecord.main.temperature}°",
//                        modifier = Modifier.weight(1f)
//                            .clip(RoundedCornerShape(DefaultCornerRadius)), // Add rounded cor
//                        textAlign = TextAlign.Start,
//                        style = MaterialTheme.typography.headlineMedium
//                    )
//
//                    // Weather icon on the right
//                    WeatherIconFromApiId(
//                        weatherApiId = weatherRecord.weather[0].iconId,
//                        modifier = Modifier.size(178.dp)
//                            .clip(RoundedCornerShape(DefaultCornerRadius)) // Add rounded corners
//                            .background(color = LightGray)
//                            .padding(DefaultPadding)
//                            .then(Modifier.align(Alignment.CenterVertically)),
//                        contentDescription = weatherRecord.weather[0].description
//                    )
//                }

                // Row for Feels Like
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.85f)
                        .background(color = SandLightest)
                        .padding(SmallPadding)
                        .border(
                            width = 1.dp,
                            color = LightGray2,
                            shape = RoundedCornerShape(DefaultCornerRadius)
                        ),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CustomText(
                        content = "Feels Like: ${weatherRecord.main.temperatureFeelsLike}°",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                            .padding(0.dp)
                            .then(Modifier.align(Alignment.CenterVertically)),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
    }

        CustomBox(
            color =PowderBlue, modifier = Modifier.weight(0.9f)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomText("High: ${weatherRecord.main.highTemperature}°")
                CustomText("Low: ${weatherRecord.main.lowTemperature}°")
                }
        }
        CustomBox(color = Silver, modifier = Modifier.weight(0.9f)) {
            CustomText("Humidity: ${weatherRecord.main.percentHumidity}%",)
        }
        CustomBox(color = PowderBlue, modifier = Modifier.weight(0.9f)) {
            CustomText( "Pressure: ${weatherRecord.main.pressure} hPa")
        }
    }
}