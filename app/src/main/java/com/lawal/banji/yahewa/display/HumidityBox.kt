package com.lawal.banji.yahewa.display

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.lawal.banji.yahewa.ui.theme.DefaultBoxColor
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.utils.CustomBox
import com.lawal.banji.yahewa.utils.CustomText

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