package com.lawal.banji.yahewa.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.R
import com.lawal.banji.yahewa.ui.theme.DefaultBorderWidth
import com.lawal.banji.yahewa.ui.theme.DefaultBoxColor
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultHeadingColor
import com.lawal.banji.yahewa.ui.theme.DefaultIconBackgroundColor
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.LargeIconSize
import com.lawal.banji.yahewa.ui.theme.LargePadding
import com.lawal.banji.yahewa.ui.theme.LargerPadding
import com.lawal.banji.yahewa.ui.theme.LightGray1
import com.lawal.banji.yahewa.ui.theme.LightGray2
import com.lawal.banji.yahewa.ui.theme.PowderBlue
import com.lawal.banji.yahewa.ui.theme.SmallPadding

fun Modifier.customBorder(
    borderColor: Color = LightGray1,
    borderWidth: Dp = DefaultBorderWidth,
    cornerRadius: Dp = DefaultCornerRadius,
): Modifier  {
    return this
        .clip(RoundedCornerShape(cornerRadius))
        .border(
            width = borderWidth,
            color = borderColor,
            shape = RoundedCornerShape(cornerRadius)
        )
}

@Composable
fun CustomRow(
    modifier: Modifier = Modifier,
    weight: Float = 1f,
    padding: Dp = 0.dp,
    cornerRadius: Dp = DefaultCornerRadius,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalAlignment: Arrangement.Horizontal = Arrangement.Start,
    backgroundColor: Color = LightGray2,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding)
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor), // Ensures backgroundColor is applied
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalAlignment,
        content = content
    )
}

/*
    * CustomText is a reusable composable function that
    * displays text with customizable padding, alignment, and style.
    * Returns a Text composable
 */
@Composable
fun CustomText(
    content: String,
    padding: Dp = DefaultPadding,
    modifier: Modifier = Modifier.fillMaxWidth(),
    textAlign: TextAlign = TextAlign.Start,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    Text(
        style = style,
        text = content,
        modifier = modifier.padding(padding),
        textAlign = textAlign,
        color = color
    )
}

@Composable
fun HeadingBox(
    title: String = stringResource(id = R.string.app_name),
    style: TextStyle =  MaterialTheme.typography.headlineLarge,
    boxColor: Color = DefaultHeadingColor,
    padding: Dp = DefaultPadding,
    cornerRadius: Dp = DefaultCornerRadius,
    boxModifier: Modifier = Modifier.fillMaxWidth(),
    textModifier: Modifier = Modifier,
) {
    Box(modifier = boxModifier.background(boxColor).padding(padding)) {
        Text(modifier = textModifier, style = style, text = title)
    }
}

@Composable
fun LoadingAnimation() {
    val loading = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loading.value) {
            CircularProgressIndicator()
        } else {
            Text(text = "Content Loaded!")
        }
    }
}

/*
    * CustomBox is a reusable composable function that creates a Box with
    * customizable properties. It allows you to set the background color, cornerRadius,
    *  padding, and content alignment.
    *
    * Returns a Box composable
*/
@Composable
fun CustomBox(
    modifier: Modifier = Modifier,
    color: Color = PowderBlue,
    cornerRadius: Dp = DefaultCornerRadius,
    padding: Dp = LargePadding,
    textAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth().then(Modifier.height(200.dp))
            .padding(horizontal = LargerPadding, vertical = SmallPadding)
            .background(color, shape = RoundedCornerShape(cornerRadius)),
        contentAlignment = textAlignment
    ) {  content()  }
}


@Composable
fun TextBox(
    text: String,
    textModifier: Modifier = Modifier,
    cornerRadius: Dp = DefaultCornerRadius,
    textAlignment: TextAlign = TextAlign.Center,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    textPadding: PaddingValues = PaddingValues(DefaultPadding),
    boxModifier: Modifier = Modifier,
    boxColor: Color = DefaultBoxColor,
    boxAlignment: Alignment = Alignment.Center,
    boxCornerRadius: Dp = DefaultCornerRadius,
    boxPadding: PaddingValues = PaddingValues(horizontal = LargerPadding, vertical = SmallPadding)
) {
    Box(
        modifier = boxModifier
            .fillMaxWidth()
//            .height(200.dp)
            .padding(boxPadding)
            .background(boxColor, shape = RoundedCornerShape(boxCornerRadius.coerceAtLeast(cornerRadius))),
        contentAlignment = boxAlignment
    ) {
        Text(
            text = text,
            style = style,
            color = textColor,
            textAlign = textAlignment,
            modifier = textModifier.padding(textPadding).fillMaxWidth()
        )
    }
}

@Composable
fun DescriptionBox(
    modifier: Modifier = Modifier,  // Allow modifier customization
    description: String = stringResource(id = R.string.default_weather_description),
    boxColor: Color = DefaultBoxColor,
    textAlign: TextAlign = TextAlign.Start,
    cornerRadius: Dp = DefaultCornerRadius,
    style: TextStyle = MaterialTheme.typography.headlineLarge,
    contentDescription: String? = null // Added for accessibility
) {
    CustomBox(
        modifier = modifier, // Pass modifier down
        color = boxColor,
        cornerRadius = cornerRadius
    ) {
        CustomText(
            content = description,
            textAlign = textAlign,
            style = style,
            modifier = Modifier.semantics {
                if (contentDescription != null) {
                    this.contentDescription = contentDescription
                }
            }
        )
    }
}

@Composable
fun HumidityBox(
    percentHumidity: Double =  AppDefault.HUMIDITY,
    boxColor: Color = DefaultBoxColor,
    textAlign: TextAlign = TextAlign.Center,
    cornerRadius: Dp = DefaultCornerRadius,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    val content: String = "$percentHumidity % humidity"
    CustomBox(color=boxColor, cornerRadius =  cornerRadius) {
        CustomText(content = content, textAlign = textAlign, style = style)
    }
}

@Composable
fun LocationBox(
    city: String = stringResource(id = R.string.default_city),
    boxColor: Color = DefaultBoxColor,
    textAlign: TextAlign = TextAlign.Center,
    cornerRadius : Dp = DefaultCornerRadius,
    style: TextStyle = MaterialTheme.typography.headlineLarge,
) {
    CustomBox(color=boxColor, cornerRadius = cornerRadius) {
        CustomText(content = city, textAlign = textAlign, style = style)
    }
}

@Composable
fun IconBox(
    iconId: String = stringResource(id = R.string.default_weather_icon_id),
    iconBackgroundColor : Color = DefaultIconBackgroundColor,
    cornerRadius: Dp = DefaultCornerRadius,
    boxColor: Color = DefaultBoxColor
) {
    CustomBox(color=boxColor, cornerRadius = cornerRadius) {
        WeatherIcon(weatherApiId = iconId
//            weatherApiId = iconId,
//            modifier = Modifier.background(iconBackgroundColor).fillMaxSize(1.0f)
        )
    }
}

@Composable
fun PressureBox(
    pressure: Double = AppDefault.PRESSURE,
    boxColor: Color = DefaultBoxColor,
    textAlign: TextAlign = TextAlign.Center,
    style: TextStyle = MaterialTheme.typography.bodySmall,
    unit: String = stringResource(id = R.string.imperial_pressure_unit),
) {
    val content: String = "$pressure $unit"
    CustomBox(color=boxColor) { CustomText(content = content,  textAlign = textAlign,  style = style) }
}

@Composable
fun TemperatureBox(
    temperature: Double = AppDefault.TEMPERATURE,
    information: String? = null,
    boxColor: Color = DefaultBoxColor,
    textAlign: TextAlign = TextAlign.Center,
    cornerRadius : Dp = DefaultCornerRadius,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    val content: String = information?.let { "$it: $temperature°" } ?: "$temperature°"
    CustomBox(color=boxColor, cornerRadius = cornerRadius) {
        CustomText(content = content, textAlign = textAlign, style = style)
    }
}

@Composable
fun WeatherIcon(
    weatherApiId: String,
    iconSize: Dp = LargeIconSize,
    cornerRadius: Dp = DefaultCornerRadius,
    backgroundColor : Color = LightGray,
    padding: Dp = DefaultPadding,
    alignment: Alignment = Alignment.Center,

//    modifier: Modifier = Modifier
//        .size(LargeIconSize)
//        .clip(RoundedCornerShape(DefaultCornerRadius))
//        .background(color = LightGray)
//        .padding(DefaultPadding),
    contentDescription: String? = null,
//    alignment: Alignment = Alignment.Center
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
        modifier = Modifier
            .padding(padding)
            .background(backgroundColor)
            .size(iconSize)
            .clip(RoundedCornerShape(cornerRadius))
    )
}