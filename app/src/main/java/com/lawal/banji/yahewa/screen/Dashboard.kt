package com.lawal.banji.yahewa.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import com.lawal.banji.yahewa.R
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.LargeCornerRadius
import com.lawal.banji.yahewa.ui.theme.Lavender
import com.lawal.banji.yahewa.ui.theme.PowderBlue
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray
import com.lawal.banji.yahewa.ui.theme.SandLightest
import com.lawal.banji.yahewa.ui.theme.Silver
import com.lawal.banji.yahewa.ui.theme.SmallPadding
import com.lawal.banji.yahewa.ui.theme.SmallerPadding
import com.lawal.banji.yahewa.utils.CustomBox
import com.lawal.banji.yahewa.utils.CustomRow
import com.lawal.banji.yahewa.utils.CustomText
import com.lawal.banji.yahewa.utils.iconFromWeatherApiId
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
        /*
        * Node Type: Box
         * Description: Displays the app name.
         * Color: BlueGray
         * Modifier: fillMaxWidth()
         * Text Style: bodySmall
         * Text Alignment: Top Start
        * */
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
                content = weatherRecord.city,
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
            Column(modifier = Modifier.fillMaxWidth().padding(DefaultPadding).weight(2f)) {

                /*
                * Child Node Type: CustomRow
                 * Description: Displays the current temperature and weather icon.
                 * Color: LightGray2
                 * Modifier: weight(1.7f)
                 * Text Style: headlineMedium
                 * Text Alignment: Start
                 *
                 * Child Node Type: CustomText
                * */
                CustomRow(modifier = Modifier.customBorder().weight(1.7f)) {
                    // Current temperature
                    CustomText(
                        content = "${weatherRecord.main.temperature}°",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.weight(1f)
                            .clip(RoundedCornerShape(DefaultCornerRadius))
                    )
                    // Weather icon
                    iconFromWeatherApiId(
                        weatherApiId = weatherRecord.weather[0].iconId,
                        modifier = Modifier.weight(1.4f).background(Lavender).fillMaxSize(1.0f),
                        contentDescription = weatherRecord.weather[0].description
                    )
                }
                // Row for Feels Like
                CustomRow(
                    backgroundColor = SandLightest,
                    horizontalAlignment = Arrangement.Center,
                    modifier = Modifier.weight(0.85f).customBorder()
                ) {
                    CustomText(
                        padding = 0.dp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        content = "Feels Like: ${weatherRecord.main.temperatureFeelsLike}°"
                    )
                }
            }
    }
        /*
        *  Node Type: CustomBox
         * Description: Displays the high and low temperature for the day.
         * Color: PowderBlue
         * Modifier: weight(0.9f)
         * Text Style: bodyMedium
         * Text Alignment: Start
         *
         * Child Node Type: Column
        * */
        CustomBox(
            color = PowderBlue, modifier = Modifier.weight(0.9f)) {
            Column(
                modifier = Modifier.padding(0.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // High temperature
                CustomText("High: ${weatherRecord.main.highTemperature}°")
                // Low temperature
                CustomText("Low: ${weatherRecord.main.lowTemperature}°")
                }
        }

        // Row containing humidity.  The humidity is stored in a CustomBox
        CustomBox(color = Silver, modifier = Modifier.weight(0.9f)) {
            CustomText("Humidity: ${weatherRecord.main.percentHumidity}%",)
        }
        // Row containing pressure. The pressure is stored in a CustomBox
        CustomBox(color = PowderBlue, modifier = Modifier.weight(0.9f)) {
            CustomText( "Pressure: ${weatherRecord.main.pressure} hPa")
        }
    }
}