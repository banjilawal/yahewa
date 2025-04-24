package com.lawal.banji.yahewa.display

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.lawal.banji.yahewa.R

import com.lawal.banji.yahewa.ui.theme.DefaultBoxColor
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.utils.CustomBox
import com.lawal.banji.yahewa.utils.CustomText

@Composable
fun DescriptionBox(
    description: String = stringResource(id =R.string.default_weather_description),
    boxColor: Color = DefaultBoxColor,
    textAlign: TextAlign = TextAlign.Start,
    cornerRadius : Dp = DefaultCornerRadius,
    style: TextStyle = MaterialTheme.typography.headlineLarge,
) {
    CustomBox(color=boxColor, cornerRadius = cornerRadius) {
        CustomText(content = description, textAlign = textAlign, style = style)
    }
}