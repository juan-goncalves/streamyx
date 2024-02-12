package com.squidat.streamyx.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val LightColorScheme = lightColorScheme(
    primary = Amber200,
    onPrimary = AlmostBlack,
    background = White,
    onBackground = AlmostBlack,
    surface = VeryLightGrey,
    onSurface = AlmostBlack,
    secondary = Grey,
    onSecondary = White,
)

val DarkColorScheme = darkColorScheme(
    primary = Amber200,
    onPrimary = RichBlack,
    background = RichBlack,
    onBackground = VeryLightGreyText,
    surface = LightAmber,
    onSurface = VeryLightGreyText,
    secondary = LightGreyText,
    onSecondary = Amber200,
)

@Composable
fun StreamyxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}