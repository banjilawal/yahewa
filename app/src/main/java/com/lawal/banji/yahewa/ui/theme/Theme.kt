package com.lawal.banji.yahewa.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Sand,             // Main brand color
    onPrimary = Black,          // Text/icons on primary
    primaryContainer = SandLightest,
    onPrimaryContainer = Black,

    secondary = Coral,
    onSecondary = White,
    secondaryContainer = SandLight,
    onSecondaryContainer = Black,

    background = LightGray3,
    onBackground = DarkGray2,

    surface = White,
    onSurface = DarkGray2,

    error = Red,
    onError = White
)

private val DarkColorScheme = darkColorScheme(
    primary = SandDark,
    onPrimary = White,
    primaryContainer = SandDarkest,
    onPrimaryContainer = White,

    secondary = Coral,
    onSecondary = Black,
    secondaryContainer = DeepOrange,
    onSecondaryContainer = White,

    background = DarkGray1,
    onBackground = LightGray2,

    surface = DarkGray2,
    onSurface = LightGray3,

    error = Red,
    onError = Black
)

@Composable
fun YahewaTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40
//
//    /* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)
//
//@Composable
//fun YahewaTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}