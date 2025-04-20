package com.lawal.banji.yahewa.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.LightGray1
import com.lawal.banji.yahewa.ui.theme.Silver

@Composable
fun CustomRow(
    modifier: Modifier = Modifier,
    weight: Float = 1f,
    padding: Dp = 0.dp,
    cornerRadius: Dp = DefaultCornerRadius,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalAlignment: Arrangement.Horizontal = Arrangement.Start,
    borderColor: Color = LightGray1,
    borderWidth: Dp = 2.dp,
    backgroundColor: Color = Silver,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(padding)
            .clip(RoundedCornerShape(cornerRadius))
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(cornerRadius)
            ),
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalAlignment,
        content = content
    )
}