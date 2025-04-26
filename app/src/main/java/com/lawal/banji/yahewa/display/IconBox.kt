package com.lawal.banji.yahewa.display

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.lawal.banji.yahewa.R
import com.lawal.banji.yahewa.ui.theme.DefaultBoxColor
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultIconBackgroundColor
import com.lawal.banji.yahewa.utils.CustomBox
import com.lawal.banji.yahewa.utils.iconFromWeatherApiId

@Composable
fun IconBox(
    iconId: String = stringResource(id = R.string.default_weather_icon_id),
    iconBackgroundColor : Color = DefaultIconBackgroundColor,
    cornerRadius: Dp = DefaultCornerRadius,
    boxColor: Color = DefaultBoxColor
) {
    CustomBox(color=boxColor, cornerRadius = cornerRadius) {
        iconFromWeatherApiId(weatherApiId = iconId
//            weatherApiId = iconId,
//            modifier = Modifier.background(iconBackgroundColor).fillMaxSize(1.0f)
        )
    }
}