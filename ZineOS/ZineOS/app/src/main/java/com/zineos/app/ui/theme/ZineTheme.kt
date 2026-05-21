package com.zineos.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.zineos.app.model.ThemePalettes
import com.zineos.app.model.ZineColors
import com.zineos.app.model.ZineTheme

val LocalZineColors = compositionLocalOf { ThemePalettes.BAUHAUS }
val LocalZineTheme = compositionLocalOf { ZineTheme.BAUHAUS }

@Composable
fun ZineOSTheme(
    theme: ZineTheme,
    content: @Composable () -> Unit
) {
    val colors = ThemePalettes.forTheme(theme)

    val m3Colors = when (theme) {
        ZineTheme.CYBERPUNK, ZineTheme.SOLARPUNK -> darkColorScheme(
            primary = colors.primary,
            onPrimary = colors.onPrimary,
            background = colors.background,
            onBackground = colors.onBackground,
            surface = colors.surface,
            onSurface = colors.onSurface,
            surfaceVariant = colors.surfaceVariant,
            secondary = colors.accent,
            onSecondary = colors.background,
            tertiary = colors.accent
        )
        else -> lightColorScheme(
            primary = colors.primary,
            onPrimary = colors.onPrimary,
            background = colors.background,
            onBackground = colors.onBackground,
            surface = colors.surface,
            onSurface = colors.onSurface,
            surfaceVariant = colors.surfaceVariant,
            secondary = colors.accent,
            onSecondary = colors.background,
            tertiary = colors.accent
        )
    }

    CompositionLocalProvider(
        LocalZineColors provides colors,
        LocalZineTheme provides theme
    ) {
        MaterialTheme(
            colorScheme = m3Colors,
            content = content
        )
    }
}
