package com.lawal.banji.yahewa.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.lawal.banji.yahewa.R
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultHeadingColor
import com.lawal.banji.yahewa.ui.theme.DefaultPadding

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