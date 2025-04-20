package com.lawal.banji.yahewa.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.lawal.banji.yahewa.ui.theme.DefaultPadding

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
