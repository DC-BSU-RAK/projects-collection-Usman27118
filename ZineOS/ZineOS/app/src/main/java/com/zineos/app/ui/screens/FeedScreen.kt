package com.zineos.app.ui.screens

import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Tune
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zineos.app.model.CardType
import com.zineos.app.model.ZineCard
import com.zineos.app.model.ZineContent
import com.zineos.app.model.ZineTheme
import com.zineos.app.ui.theme.LocalZineColors
import com.zineos.app.ui.theme.LocalZineTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    onNavigateToPreferences: () -> Unit
) {
    val colors = LocalZineColors.current
    val theme = LocalZineTheme.current
    val cards = ZineContent.cardsForTheme(theme)
    var showInfoDialog by remember { mutableStateOf(false) }

    if (showInfoDialog) {
        InfoDialog(onDismiss = { showInfoDialog = false })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "ZINE.OS",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Black,
                            fontSize = 18.sp,
                            color = colors.onBackground,
                            letterSpacing = 4.sp
                        )
                        Text(
                            text = theme.tagline,
                            fontFamily = FontFamily.Serif,
                            fontStyle = FontStyle.Italic,
                            fontSize = 11.sp,
                            color = colors.accent
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
                    IconButton(onClick = onNavigateToPreferences) {
                        Icon(
                            imageVector = Icons.Outlined.Tune,
                            contentDescription = "Preferences",
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
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ThemeHeaderBanner(theme = theme, colors = colors)
            }
            items(cards) { card ->
                ZineCardItem(card = card, colors = colors, theme = theme)
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun ThemeHeaderBanner(
    theme: ZineTheme,
    colors: com.zineos.app.model.ZineColors
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.primary)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = theme.emoji,
                    fontSize = 28.sp,
                    color = colors.onPrimary
                )
                Text(
                    text = theme.displayName.uppercase(),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Black,
                    fontSize = 22.sp,
                    color = colors.onPrimary,
                    letterSpacing = 3.sp
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "ISSUE 001",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp,
                    color = colors.onPrimary.copy(alpha = 0.6f),
                    letterSpacing = 2.sp
                )
                Text(
                    text = "${cards().size} PIECES",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp,
                    color = colors.onPrimary.copy(alpha = 0.6f),
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

private fun cards(): List<Int> = List(6) { it }

@Composable
private fun ZineCardItem(
    card: ZineCard,
    colors: com.zineos.app.model.ZineColors,
    theme: ZineTheme
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.cardBackground)
            .border(
                width = if (card.type == CardType.MANIFESTO || card.type == CardType.TYPOGRAPHY) 2.dp else 1.dp,
                color = if (card.type == CardType.MANIFESTO) colors.accent else colors.divider.copy(alpha = 0.5f)
            )
            .clickable { expanded = !expanded }
            .padding(20.dp)
            .animateContentSize()
    ) {
        // Card label / category
        Text(
            text = card.label,
            fontFamily = FontFamily.Monospace,
            fontSize = 9.sp,
            color = colors.accent,
            letterSpacing = 2.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Headline
        Text(
            text = card.headline,
            fontFamily = when (card.type) {
                CardType.TYPOGRAPHY, CardType.MANIFESTO -> FontFamily.Monospace
                else -> FontFamily.Serif
            },
            fontWeight = when (card.type) {
                CardType.TYPOGRAPHY -> FontWeight.Black
                CardType.MANIFESTO -> FontWeight.Bold
                else -> FontWeight.Normal
            },
            fontSize = when (card.type) {
                CardType.TYPOGRAPHY -> 24.sp
                CardType.MANIFESTO -> 20.sp
                CardType.FRAGMENT -> 18.sp
                else -> 20.sp
            },
            color = colors.onBackground,
            lineHeight = when (card.type) {
                CardType.TYPOGRAPHY -> 30.sp
                else -> 27.sp
            },
            letterSpacing = when (card.type) {
                CardType.TYPOGRAPHY -> 1.sp
                CardType.MANIFESTO -> 0.5.sp
                else -> 0.sp
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Divider
        Box(
            modifier = Modifier
                .width(if (expanded) 40.dp else 24.dp)
                .height(2.dp)
                .background(colors.accent)
                .animateContentSize()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Body text
        Text(
            text = card.body,
            fontFamily = FontFamily.Serif,
            fontSize = 15.sp,
            color = colors.onSurface,
            lineHeight = 24.sp,
            maxLines = if (expanded) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis
        )

        if (!expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "[ TAP TO READ ]",
                fontFamily = FontFamily.Monospace,
                fontSize = 9.sp,
                color = colors.accent.copy(alpha = 0.7f),
                letterSpacing = 2.sp
            )
        } else {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "[ COLLAPSE ]",
                fontFamily = FontFamily.Monospace,
                fontSize = 9.sp,
                color = colors.accent.copy(alpha = 0.7f),
                letterSpacing = 2.sp
            )
        }
    }
}
