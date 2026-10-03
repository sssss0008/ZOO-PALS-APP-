package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = SunGoldNight,
    onPrimary = Color.Black,
    primaryContainer = SunGoldDark,
    onPrimaryContainer = Color.White,
    secondary = JungleNight,
    onSecondary = Color.Black,
    secondaryContainer = JungleGreen,
    onSecondaryContainer = Color.White,
    tertiary = CoralNight,
    background = DarkBackground,
    surface = DarkSurface,
    surfaceVariant = Color(0xFF38353E),
    onBackground = Color(0xFFECE0DA),
    onSurface = Color(0xFFECE0DA),
    onSurfaceVariant = Color(0xFFCAC4D0)
)

private val LightColorScheme = lightColorScheme(
    primary = SunGoldDark,
    onPrimary = Color.White,
    primaryContainer = SoftPeach,
    onPrimaryContainer = Color(0xFF5D2E00),
    secondary = JungleGreen,
    onSecondary = Color.White,
    secondaryContainer = LeafMint,
    onSecondaryContainer = Color(0xFF0F3814),
    tertiary = CoralTangerine,
    onTertiary = Color.White,
    tertiaryContainer = LightYellow,
    onTertiaryContainer = Color(0xFF4A1A00),
    background = WarmCream,
    surface = WarmCard,
    surfaceVariant = Color(0xFFF7F2E7),
    onBackground = Color(0xFF2C241E),
    onSurface = Color(0xFF2C241E),
    onSurfaceVariant = Color(0xFF5B4F47)
)

@Composable
fun ZooPalsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep cheerful consistent brand palette for kids
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
