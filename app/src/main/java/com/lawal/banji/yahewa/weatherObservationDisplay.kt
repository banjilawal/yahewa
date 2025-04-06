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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopStart
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
    if (icon != null) {
        Image(
            bitmap = icon.toBitmap().asImageBitmap(),
            contentDescription = "Weather Icon",
            modifier = Modifier
                .padding((0.dp))
                .size(dimensionResource(id = R.dimen.default_font_size))
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.farenheit),
            contentDescription = "Default Weather Icon",
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.default_font_size))
                .background(Color.White)
        )
    }
}

@Composable
fun DisplayCurrentConditions(
    currentTemperature: Temperature,
    feelsLikeTemperature: Temperature,
    icon: Drawable?
) {
    Column(modifier =  Modifier.padding(dimensionResource(id = R.dimen.default_padding))) {
        WeatherObservationRow(currentTemperature.toString(), Modifier.padding(0.dp), centered = true)
        WeatherObservationRow(feelsLikeTemperature.toString(), Modifier.padding(0.dp), centered = true)
        DisplayIcon(icon)
    }

}

@Composable
fun WeatherObservationRow(value: String, paddingModifier: Modifier, centered: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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
fun WeatherObservationDisplay(weatherObservation: WeatherObservation) {
    Column(modifier = Modifier.padding(16.dp)) {
        WeatherObservationRow(stringResource(id = R.string.app_name), Modifier.padding(dimensionResource(id = R.dimen.default_padding)), centered = true)
        WeatherObservationRow(weatherObservation.location.toString(), Modifier.padding(dimensionResource(id = R.dimen.default_padding)), centered = true)
        DisplayCurrentConditions(
            currentTemperature = weatherObservation.currentTemperature,
            feelsLikeTemperature = weatherObservation.feelsLikeTemperature,
            icon = weatherObservation.icon
        )
        WeatherObservationRow("Low " + weatherObservation.lowTemperature.toString(), Modifier.padding(dimensionResource(id = R.dimen.default_padding)))
        WeatherObservationRow("High " + weatherObservation.highTemperature.toString(), Modifier.padding(dimensionResource(id = R.dimen.default_padding)))
        WeatherObservationRow("Humidity " +  weatherObservation.percentHumidity + "%", Modifier.padding(dimensionResource(id = R.dimen.default_padding)))
        WeatherObservationRow("Pressure " + weatherObservation.pressure + " hPa", Modifier.padding(dimensionResource(id = R.dimen.default_padding)))
    }
}