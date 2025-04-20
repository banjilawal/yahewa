package com.lawal.banji.yahewa.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.LargePadding
import com.lawal.banji.yahewa.ui.theme.LargerPadding
import com.lawal.banji.yahewa.ui.theme.PowderBlue
import com.lawal.banji.yahewa.ui.theme.SmallPadding

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