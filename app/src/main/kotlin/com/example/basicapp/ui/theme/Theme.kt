package com.example.basicapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8DB5FF),
    surface = Color(0xFF1E1E1E),
    background = Color(0xFF121212),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0067C0),
    surface = Color(0xFFFFFFFF),
    background = Color(0xFFF5F5F5),
)

@Composable
fun BasicAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}