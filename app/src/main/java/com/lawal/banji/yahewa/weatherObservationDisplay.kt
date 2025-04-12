package com.lawal.banji.yahewa

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun FormattedText(
    text: String,
    paddingModifier: Modifier,
    gravityModifier: Modifier
) {
    val fontSize = dimensionResource(id = R.dimen.default_font_size).value.sp
    val height = dimensionResource(id = R.dimen.default_height)
    Text(
        text = text,
        style = TextStyle(fontSize = fontSize),
        modifier = paddingModifier
            .then(gravityModifier)
            .then(Modifier.height(dimensionResource(id = R.dimen.default_height)))
    )
}

@Composable
fun DisplayIcon(icon: Drawable?) {
    val iconSize = dimensionResource(id = R.dimen.default_font_size) * 4
    if (icon != null) {
        Image(
            bitmap = icon.toBitmap().asImageBitmap(),
            contentDescription = "Weather Icon",
            modifier = Modifier
                .padding((0.dp))
                .size(iconSize)
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.farenheit),
            contentDescription = "Default Weather Icon",
            modifier = Modifier
                .size(iconSize)
                .background(Color.White)
        )
    }
}

@Composable
fun DisplayCurrentConditions(
    currentTemperature: Double,
    feelsLikeTemperature: Double,
    icon: Drawable?
) {
    Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.default_padding))) {
        Column(modifier = Modifier.weight(3f).wrapContentWidth(Alignment.Start).then(Modifier.padding(0.dp))) {
            WeatherObservationRow(currentTemperature.toString(), centered = true, backgroundColor = Color.White, paddingModifier = Modifier.padding(0.dp))
            WeatherObservationRow("Feels like " + feelsLikeTemperature.toString(), centered = false, backgroundColor = Color.White, paddingModifier = Modifier.padding(0.dp))
        }
        Column(modifier = Modifier.weight(1f).then(Modifier.padding(0.dp)).wrapContentWidth(Alignment.Start)) {
            DisplayIcon(icon)
        }
    }
}

@Composable
fun WeatherObservationRow(
    value: String,
    centered: Boolean = false,
    backgroundColor: Color = Color.White,
    paddingModifier: Modifier = Modifier.padding(dimensionResource(id = R.dimen.default_padding))

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .then(paddingModifier), // 15dp spacing between rows
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (centered) Arrangement.Center else Arrangement.Start
    ) {
        Text(
            text = value,
            fontSize = dimensionResource(id = R.dimen.default_font_size).value.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun WeatherObservationDisplay(weatherObservation: OpenWeatherResponse) {
    Column(modifier = Modifier.padding(16.dp)) {
        WeatherObservationRow(stringResource(id = R.string.app_name), centered = false, backgroundColor = colorResource(id = R.color.headingColor))
        WeatherObservationRow(weatherObservation.location.toString(), centered = true)
        DisplayCurrentConditions(
            currentTemperature = weatherObservation.currentTemperature,
            feelsLikeTemperature = weatherObservation.feelsLikeTemperature,
            icon = weatherObservation.icon
        )
        WeatherObservationRow("Low " + weatherObservation.lowTemperature.toString())
        WeatherObservationRow("High " + weatherObservation.highTemperature.toString())
        WeatherObservationRow("Humidity " +  weatherObservation.percentHumidity + "%")
        WeatherObservationRow("Pressure " + weatherObservation.pressure + " hPa")
    }
}