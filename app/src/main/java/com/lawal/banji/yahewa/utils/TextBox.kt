package com.lawal.banji.yahewa.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.ui.theme.DefaultBoxColor
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.LargerPadding
import com.lawal.banji.yahewa.ui.theme.SmallPadding

@Composable
fun TextBox(
    text: String,
    modifier: Modifier = Modifier,
    boxColor: Color = DefaultBoxColor,
    cornerRadius: Dp = DefaultCornerRadius,
    textAlignment: TextAlign = TextAlign.Center,
    boxAlignment: Alignment = Alignment.Center,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    boxPadding: PaddingValues = PaddingValues(horizontal = LargerPadding, vertical = SmallPadding),
    textPadding: PaddingValues = PaddingValues(DefaultPadding)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(boxPadding)
            .background(boxColor, shape = RoundedCornerShape(cornerRadius)),
        contentAlignment = boxAlignment
    ) {
        Text(
            text = text,
            style = style,
            color = textColor,
            textAlign = textAlignment,
            modifier = Modifier.padding(textPadding)
        )
    }
}
