package com.dov4k1n.tatarapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(
    primary = Color(0xFFFFFFFF), // main objects and main text
    secondary = Color(0xFFF6A849), // affixes
    tertiary = Color(0xFF39A75F), // progress bar
    primaryContainer = Color(0xFF202F36), // main cards
    onPrimaryContainer = Color(0xFF1A2930), // opened list item
    error = Color(0xFFFC6755),
    onError = Color(0xFF4897D9), // phonetics tab
    inversePrimary = Color(0xFFFCB9B1), // lexicon tab
    onSecondary = Color(0xFFF6A849), // morphology tab
    errorContainer = Color(0xFF5AAA95), // syntax tab
    onErrorContainer = Color(0xFFF8F991), // culture tab
    background = Color(0xFF131F24),
    onSecondaryContainer = Color(0xFF131F24), // letters on secondary and tertiary in alphabet theory
    onTertiary = Color(0xFF202F36), // for their ^ sounds
    onBackground = Color(0xFFAFAFAF), // text on background + ui elements
    onSurfaceVariant = Color(0xB3AFAFAF), // for Task Message on Conjugation Card
    outlineVariant = Color(0xFF8D96A0), // for BottomNavigation
)

private val LightColors = lightColorScheme(
    primary = Color(0xFFFFFFFF), // main objects and main text
    secondary = Color(0xFFD7D704), // affixes
    tertiary = Color(0xFF39A75F), // progress bar
    primaryContainer = Color(0xFF004A86), // main cards
    onPrimaryContainer = Color(0xFF003B77), // opened list item
    error = Color(0xFFFC6755),
    onError = Color(0xFFFFFFFF), // phonetics tab
    inversePrimary = Color(0xFFFFFFFF), // lexicon tab
    onSecondary = Color(0xFFFFFFFF), // morphology tab
    errorContainer = Color(0xFFFFFFFF), // syntax tab
    onErrorContainer = Color(0xFFFFFFFF), // culture tab
    background = Color(0xFF1A69AB),
    onSecondaryContainer = Color(0xFF131F24), // letters on secondary and tertiary in alphabet theory
    onTertiary = Color(0xFF202F36), // for their ^ sounds
    onBackground = Color(0xFF94C4F4), // text on background + ui elements
    onSurfaceVariant = Color(0xB394C4F4), // for Task Message on Conjugation Card
    outlineVariant = Color(0xFF94C4F4), // for BottomNavigation
)

@Composable
fun TatarAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}