package com.zineos.app.model

import androidx.compose.ui.graphics.Color

enum class ZineTheme(
    val displayName: String,
    val tagline: String,
    val emoji: String
) {
    BAUHAUS(
        displayName = "Bauhaus",
        tagline = "Form follows function.",
        emoji = "◼"
    ),
    CYBERPUNK(
        displayName = "Cyberpunk",
        tagline = "The future is neon.",
        emoji = "⬡"
    ),
    WABI_SABI(
        displayName = "Wabi-Sabi",
        tagline = "Beauty in impermanence.",
        emoji = "○"
    ),
    BRUTALIST(
        displayName = "Brutalist",
        tagline = "Raw. Honest. Concrete.",
        emoji = "▣"
    ),
    SOLARPUNK(
        displayName = "Solarpunk",
        tagline = "Futures worth building.",
        emoji = "✦"
    );

    companion object {
        fun fromName(name: String): ZineTheme =
            entries.firstOrNull { it.name == name } ?: BAUHAUS
    }
}

data class ZineColors(
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val primary: Color,
    val onPrimary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val accent: Color,
    val cardBackground: Color,
    val divider: Color
)

object ThemePalettes {
    val BAUHAUS = ZineColors(
        background = Color(0xFFF5F0E8),
        surface = Color(0xFFEEE8DA),
        surfaceVariant = Color(0xFFDDD5C5),
        primary = Color(0xFF1A1A1A),
        onPrimary = Color(0xFFF5F0E8),
        onBackground = Color(0xFF1A1A1A),
        onSurface = Color(0xFF2A2A2A),
        accent = Color(0xFFCC2200),
        cardBackground = Color(0xFFEEE8DA),
        divider = Color(0xFF1A1A1A)
    )
    val CYBERPUNK = ZineColors(
        background = Color(0xFF050510),
        surface = Color(0xFF0D0D1E),
        surfaceVariant = Color(0xFF141428),
        primary = Color(0xFF00FFE1),
        onPrimary = Color(0xFF050510),
        onBackground = Color(0xFFE0E0FF),
        onSurface = Color(0xFFBBBBDD),
        accent = Color(0xFFFF2D6B),
        cardBackground = Color(0xFF0D0D1E),
        divider = Color(0xFF00FFE1)
    )
    val WABI_SABI = ZineColors(
        background = Color(0xFFF2EDE4),
        surface = Color(0xFFE8E0D4),
        surfaceVariant = Color(0xFFDDD5C8),
        primary = Color(0xFF4A3F35),
        onPrimary = Color(0xFFF2EDE4),
        onBackground = Color(0xFF3A3028),
        onSurface = Color(0xFF5C4F43),
        accent = Color(0xFF8B7355),
        cardBackground = Color(0xFFE8E0D4),
        divider = Color(0xFFBBAA95)
    )
    val BRUTALIST = ZineColors(
        background = Color(0xFFE8E4DC),
        surface = Color(0xFFDDD8CE),
        surfaceVariant = Color(0xFFCCC6BA),
        primary = Color(0xFF111111),
        onPrimary = Color(0xFFE8E4DC),
        onBackground = Color(0xFF111111),
        onSurface = Color(0xFF222222),
        accent = Color(0xFFFF5500),
        cardBackground = Color(0xFFDDD8CE),
        divider = Color(0xFF111111)
    )
    val SOLARPUNK = ZineColors(
        background = Color(0xFF1A2410),
        surface = Color(0xFF223018),
        surfaceVariant = Color(0xFF2C3C20),
        primary = Color(0xFF8FD46A),
        onPrimary = Color(0xFF1A2410),
        onBackground = Color(0xFFD4E8C0),
        onSurface = Color(0xFFB8D89A),
        accent = Color(0xFFFFCC44),
        cardBackground = Color(0xFF223018),
        divider = Color(0xFF8FD46A)
    )

    fun forTheme(theme: ZineTheme): ZineColors = when (theme) {
        ZineTheme.BAUHAUS -> BAUHAUS
        ZineTheme.CYBERPUNK -> CYBERPUNK
        ZineTheme.WABI_SABI -> WABI_SABI
        ZineTheme.BRUTALIST -> BRUTALIST
        ZineTheme.SOLARPUNK -> SOLARPUNK
    }
}
