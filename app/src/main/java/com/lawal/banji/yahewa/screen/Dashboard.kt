package com.lawal.banji.yahewa.screen

import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.lawal.banji.yahewa.R
import com.lawal.banji.yahewa.ui.theme.BattleShipGrayBlue
import com.lawal.banji.yahewa.ui.theme.BlueGray
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.DefaultVerticalHeight
import com.lawal.banji.yahewa.ui.theme.LargeCornerRadius
import com.lawal.banji.yahewa.ui.theme.LargePadding
import com.lawal.banji.yahewa.ui.theme.LargerPadding
import com.lawal.banji.yahewa.ui.theme.LightGray1
import com.lawal.banji.yahewa.ui.theme.LightGray2
import com.lawal.banji.yahewa.ui.theme.LightGray3
import com.lawal.banji.yahewa.ui.theme.LightSalmon
import com.lawal.banji.yahewa.ui.theme.LightSalmonPink
import com.lawal.banji.yahewa.ui.theme.PowderBlue
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray
import com.lawal.banji.yahewa.ui.theme.SandLighter
import com.lawal.banji.yahewa.ui.theme.SandLightest
import com.lawal.banji.yahewa.ui.theme.Silver
import com.lawal.banji.yahewa.ui.theme.SmallPadding
import com.lawal.banji.yahewa.ui.theme.SmallerPadding
import com.lawal.banji.yahewa.ui.theme.SmallestPadding
import com.lawal.banji.yahewa.utils.WeatherIconFromApiId

import com.lawal.banji.yahewa.weather.model.WeatherRecord

private val commonBoxModifier = Modifier
    .fillMaxWidth()
    .padding(8.dp)



@Composable
fun CustomText(
    content: String,
    padding: Dp = DefaultPadding,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start, // Default text alignment
    style:  TextStyle = MaterialTheme.typography.bodyMedium // Default typography
) {
    Text(
        style = style,
        text = content,
        modifier = modifier.fillMaxWidth().padding(padding),
        textAlign = textAlign
    )
}

@Composable
fun CustomBox(
    modifier: Modifier = Modifier,
    color: Color = PowderBlue,
    cornerRadius: Dp = DefaultCornerRadius,
    padding: Dp = LargePadding,
    textAlignment: Alignment = Alignment.Center, // Use androidx.compose.ui.Alignment
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth().then(Modifier.height(200.dp))
            .padding(horizontal = LargerPadding, vertical = SmallPadding)
            .background(color, shape = RoundedCornerShape(cornerRadius)),
        contentAlignment = textAlignment // Pass the correct Alignment
    ) {  content()  }
}

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

        CustomBox(color = PowderBlue, modifier = Modifier.weight(0.75f)) {
            CustomText(
                content = weatherRecord.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
        }
        CustomBox(color = Silver, modifier = Modifier.weight(1.6f)) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(DefaultPadding).weight(1.8f)
            ) {
                // Row for Temperature and Weather Icon
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .weight(1.8f)
                        .background(Silver)
                        .padding(1.dp)
                        .clip(RoundedCornerShape(DefaultCornerRadius) )
                        .border(
                            width = 2.dp,
                            color = LightGray1,
                            shape = RoundedCornerShape(DefaultCornerRadius)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Temperature on the left
                    CustomText(
                        content = "${weatherRecord.main.temperature}°",
                        modifier = Modifier.weight(1f)
                            .clip(RoundedCornerShape(DefaultCornerRadius)), // Add rounded cor
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    // Weather icon on the right
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

//        Spacer(modifier = Modifier.height(DefaultVerticalHeight))
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