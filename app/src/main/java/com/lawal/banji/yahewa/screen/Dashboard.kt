package com.lawal.banji.yahewa.screen

import android.text.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.lawal.banji.yahewa.R
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.DefaultVerticalHeight
import com.lawal.banji.yahewa.ui.theme.LightBlue
import com.lawal.banji.yahewa.ui.theme.LightSalmon
import com.lawal.banji.yahewa.ui.theme.PaleGreen
import com.lawal.banji.yahewa.ui.theme.PowderBlue
import com.lawal.banji.yahewa.ui.theme.SandLightest
import com.lawal.banji.yahewa.ui.theme.Typography

import com.lawal.banji.yahewa.weather.model.free.WeatherRecord

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
                .background(PowderBlue)// Adds padding equal to the height of the status bar
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth() // Makes the Box span the full width of the screen // Adds padding equal to the height of the status bar
                .padding(top = 2.dp) // Adds 1 pixel of additional padding below the status bar
                .clip(RoundedCornerShape(3.dp)) // Applies rounded corners with a 2-pixel arc
                .background(Color(0xFFFFA07A)) // Salmon color
                .padding(8.dp) // Optional padding for spacing
        ) {
            Text(
                text = weatherRecord.name,
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Spacer(modifier = Modifier.height(DefaultVerticalHeight))
        CustomBox(cornerRadius = 5.dp) {
            CustomText("${weatherRecord.main.temperature}°")
        }
        CustomBox(color = Color.LightGray) {
            CustomText(style = Typography.titleLarge, content = "Feels Like: ${weatherRecord.main.temperatureFeelsLike}°")
        }
        Spacer(modifier = Modifier.height(DefaultVerticalHeight))
        CustomBox(color = SandLightest) {
            CustomText("High: ${weatherRecord.main.highTemperature}°")
        }
        CustomBox(color = SandLightest) {
            CustomText("Low: ${weatherRecord.main.lowTemperature}°")
        }
        CustomBox(color = LightSalmon) {
            CustomText("Humidity: ${weatherRecord.main.percentHumidity}%",)
        }
        CustomBox(color = LightSalmon) {
            CustomText( "Pressure: ${weatherRecord.main.pressure} hPa")
        }
        CustomBox(color = LightBlue) {
            CustomText("Wind Speed: ${weatherRecord.wind.speed} mph")
        }
        CustomBox(color = LightBlue) {
            CustomText("Wind Direction: ${weatherRecord.wind.direction} °",)
        }
    }
}