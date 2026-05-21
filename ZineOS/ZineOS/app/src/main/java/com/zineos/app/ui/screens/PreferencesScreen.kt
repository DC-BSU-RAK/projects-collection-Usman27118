package com.zineos.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zineos.app.model.ThemePalettes
import com.zineos.app.model.ZineTheme
import com.zineos.app.ui.theme.LocalZineColors
import com.zineos.app.ui.theme.LocalZineTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferencesScreen(
    currentTheme: ZineTheme,
    onThemeSelected: (ZineTheme) -> Unit,
    onNavigateBack: () -> Unit
) {
    val colors = LocalZineColors.current
    var showInfoDialog by remember { mutableStateOf(false) }

    if (showInfoDialog) {
        InfoDialog(onDismiss = { showInfoDialog = false })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "PREFERENCES",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Black,
                        fontSize = 16.sp,
                        color = colors.onBackground,
                        letterSpacing = 4.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = colors.onBackground
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showInfoDialog = true }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "App Info",
                            tint = colors.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colors.background
                )
            )
        },
        containerColor = colors.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column {
                    Text(
                        text = "CHOOSE YOUR",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = colors.onBackground.copy(alpha = 0.5f),
                        letterSpacing = 3.sp
                    )
                    Text(
                        text = "AESTHETIC",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Black,
                        fontSize = 32.sp,
                        color = colors.onBackground,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Your selection shapes the visual language and editorial content of the entire zine. This choice is saved automatically.",
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp,
                        color = colors.onSurface.copy(alpha = 0.7f),
                        lineHeight = 21.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(colors.divider.copy(alpha = 0.4f))
                    )
                }
            }

            items(ZineTheme.entries) { theme ->
                ThemeCard(
                    theme = theme,
                    isSelected = theme == currentTheme,
                    onSelect = { onThemeSelected(theme) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "ZINE.OS — ISSUE 001 — ALL CONTENT CHANGES WITH THEME",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 9.sp,
                    color = colors.onBackground.copy(alpha = 0.3f),
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

@Composable
private fun ThemeCard(
    theme: ZineTheme,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    val globalColors = LocalZineColors.current
    val themeColors = ThemePalettes.forTheme(theme)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) globalColors.accent else globalColors.divider.copy(alpha = 0.4f)
            )
            .background(if (isSelected) globalColors.surfaceVariant else globalColors.cardBackground)
    ) {
        // Color preview strip
        Box(
            modifier = Modifier
                .width(6.dp)
                .matchParentSize()
                .background(themeColors.accent)
                .align(Alignment.CenterStart)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = theme.emoji,
                        fontSize = 18.sp,
                        color = globalColors.onBackground
                    )
                    Text(
                        text = theme.displayName.uppercase(),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = globalColors.onBackground,
                        letterSpacing = 2.sp
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = theme.tagline,
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 13.sp,
                    color = globalColors.onSurface.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(10.dp))
                // Mini color palette swatches
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    listOf(
                        themeColors.background,
                        themeColors.primary,
                        themeColors.accent,
                        themeColors.surface,
                        themeColors.onSurface
                    ).forEach { swatchColor ->
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(swatchColor)
                                .border(0.5.dp, globalColors.divider.copy(alpha = 0.3f))
                        )
                    }
                }
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = "Selected",
                    tint = globalColors.accent,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .size(22.dp)
                )
            }
        }
    }
}
