package com.example.jamble_challenge.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    background = BgPrimary,
    surface = BgPrimary,
    primary = BrandPrimary,
    onPrimary = Color.White,

    onBackground = TextPrimary,
    onSurface = TextPrimary,

    outline = BorderSecondary
)

@Composable
fun JambleTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}