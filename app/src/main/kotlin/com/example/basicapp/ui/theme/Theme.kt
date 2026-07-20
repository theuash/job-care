package com.example.basicapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Indigo = Color(0xFF6366F1)
val Cyan = Color(0xFF06B6D4)
val Emerald = Color(0xFF10B981)
val Amber = Color(0xFFF59E0B)
val Rose = Color(0xFFEF4444)
val Purple = Color(0xFF8B5CF6)

private val DarkColorScheme = darkColorScheme(
    primary = Indigo,
    onPrimary = Color.White,
    primaryContainer = Indigo.copy(alpha = 0.15f),
    secondary = Cyan,
    surface = Color(0xFF1C1C1E),
    surfaceVariant = Color(0xFF2C2C2E),
    onSurface = Color(0xFFF2F2F7),
    onSurfaceVariant = Color(0xFFEBEBF0),
    background = Color(0xFF000000),
    onBackground = Color(0xFFF2F2F7),
)

private val LightColorScheme = lightColorScheme(
    primary = Indigo,
    onPrimary = Color.White,
    primaryContainer = Indigo.copy(alpha = 0.1f),
    secondary = Cyan,
    surface = Color.White,
    surfaceVariant = Color(0xFFF2F2F7),
    onSurface = Color(0xFF1C1C1E),
    onSurfaceVariant = Color(0xFF2C2C2E),
    background = Color(0xFFF8F8FA),
    onBackground = Color(0xFF1C1C1E),
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