package com.lawal.banji.yahewa.screen

import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.lawal.banji.yahewa.R
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.DefaultVerticalHeight
import com.lawal.banji.yahewa.ui.theme.LightGray1
import com.lawal.banji.yahewa.ui.theme.LightSalmon
import com.lawal.banji.yahewa.ui.theme.PowderBlue
import com.lawal.banji.yahewa.ui.theme.SandLightest
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
    color: Color = Color.White,
    cornerRadius: Dp = DefaultCornerRadius,
    padding: Dp = DefaultPadding,
    textAlignment: Alignment = Alignment.Center, // Use androidx.compose.ui.Alignment
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding)
            .background(color, shape = RoundedCornerShape(cornerRadius)),
        contentAlignment = textAlignment // Pass the correct Alignment
    ) {  content()  }
}

@Composable
fun WeatherDetailsDisplay(weatherRecord: WeatherRecord) {

    val defaultPadding = dimensionResource(id = R.dimen.default_padding)
    val defaultHeight = dimensionResource(id = R.dimen.default_height)
    val defaultFontSize = dimensionResource(id = R.dimen.default_font_size).value.sp
    val commonTextStyle = MaterialTheme.typography.bodyLarge.fontStyle



//    Column(Modifier.padding(16.dp)) {
//        val iconUrl = "https://openweathermap.org/img/wn/${weatherRecord.weather[0].iconId}@2x.png"
//        Log.d("WeatherDetailsDisplay", "Icon URL: $iconUrl")
//        Image(
//            painter = rememberAsyncImagePainter(model = iconUrl),
//            contentDescription = "Weather Icon",
//            modifier = Modifier
//                .size(100.dp)
//                .padding(16.dp),
//            contentScale = ContentScale.Fit
//        )
//    }
    Column(
        modifier = Modifier .fillMaxWidth()
                .padding(0.dp)
                .statusBarsPadding()
                .background(LightGray1)// Adds padding equal to the height of the status bar
    ) {
        CustomBox(color= PowderBlue, modifier = Modifier.weight(1.3f)) {
            CustomText(
                content = weatherRecord.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
        }
//        Spacer(modifier = Modifier.height(DefaultVerticalHeight))
        CustomBox() {
            Row(
                modifier = Modifier.fillMaxWidth().padding(DefaultPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Temperature on the left
                CustomText(
                    content = "${weatherRecord.main.temperature}°",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.headlineMedium
                )

                // Weather icon on the right
                WeatherIconFromApiId(
                    weatherApiId = weatherRecord.weather[0].iconId,
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    contentDescription = weatherRecord.weather[0].description
                )
            }
//            CustomText("${weatherRecord.main.temperature}°")
//        }
//        CustomBox(color = SandLightest, modifier = Modifier.weight(1f)) {
//            WeatherIconFromApiId(
//                weatherApiId = weatherRecord.weather[0].iconId,
//                modifier = Modifier.padding(16.dp),
//                contentDescription = "Weather Icon"
//            )
//            CustomText(content = weatherRecord.weather[0].iconId)
        }
        CustomBox(modifier = Modifier.weight(1f)) {
            CustomText(content = "Feels Like: ${weatherRecord.main.temperatureFeelsLike}°")
        }
        Spacer(modifier = Modifier.height(DefaultVerticalHeight))
        CustomBox(color = SandLightest, modifier = Modifier.weight(1f)) {
            CustomText("High: ${weatherRecord.main.highTemperature}°")
        }
        CustomBox(color = SandLightest, modifier = Modifier.weight(1f)) {
            CustomText("Low: ${weatherRecord.main.lowTemperature}°")
        }
        CustomBox(color = LightSalmon) {
            CustomText("Humidity: ${weatherRecord.main.percentHumidity}%",)
        }
        CustomBox(color = LightSalmon, modifier = Modifier.weight(1f)) {
            CustomText( "Pressure: ${weatherRecord.main.pressure} hPa")
        }
//        CustomBox(color = LightBlue) {
//            CustomText("Wind Speed: ${weatherRecord.wind.speed} mph")
//        }
//        CustomBox(color = LightBlue) {
//            CustomText("Wind Direction: ${weatherRecord.wind.direction} °",)
//        }
    }
}