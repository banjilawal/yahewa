package com.lawal.banji.yahewa.display

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.lawal.banji.yahewa.R
import com.lawal.banji.yahewa.ui.theme.DefaultBoxColor
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.utils.CustomBox
import com.lawal.banji.yahewa.utils.CustomText

@Composable
fun PressureBox(
    pressure: Double = AppDefault.PRESSURE,
    boxColor: Color = DefaultBoxColor,
    textAlign: TextAlign = TextAlign.Center,
    style: TextStyle = MaterialTheme.typography.bodySmall,
    unit: String = stringResource(id = R.string.imperial_pressure_unit),
) {
    val content: String = "$pressure $unit"
    CustomBox(color=boxColor) { CustomText(content = content,  textAlign = textAlign,  style = style) }
}