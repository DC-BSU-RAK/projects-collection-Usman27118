package com.zineos.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zineos.app.ui.theme.LocalZineColors
import com.zineos.app.ui.theme.LocalZineTheme

@Composable
fun InfoDialog(onDismiss: () -> Unit) {
    val colors = LocalZineColors.current
    val theme = LocalZineTheme.current

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = colors.surface,
        shape = RoundedCornerShape(4.dp),
        title = {
            Column {
                Text(
                    text = "ZINE.OS",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Black,
                    fontSize = 22.sp,
                    color = colors.onBackground,
                    letterSpacing = 4.sp
                )
                Text(
                    text = "v1.0 — Digital Zine Platform",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    color = colors.accent,
                    letterSpacing = 1.sp
                )
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colors.divider.copy(alpha = 0.4f))
                )
                Text(
                    text = "WHAT IS THIS?",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = colors.accent,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "Zine.OS is a minimalist digital zine — a curated collection of micro-essays, manifestos, and typographic art, styled to your chosen aesthetic sub-culture.",
                    fontFamily = FontFamily.Serif,
                    fontSize = 14.sp,
                    color = colors.onSurface,
                    lineHeight = 22.sp
                )
                Text(
                    text = "HOW TO USE",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = colors.accent,
                    letterSpacing = 2.sp
                )
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    InstructionRow("01", "Browse the Feed to read today's curated content.", colors.onSurface, colors.accent)
                    InstructionRow("02", "Tap Preferences to choose your design sub-culture.", colors.onSurface, colors.accent)
                    InstructionRow("03", "Your chosen theme persists between sessions.", colors.onSurface, colors.accent)
                    InstructionRow("04", "Each theme unlocks a unique set of essays.", colors.onSurface, colors.accent)
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colors.divider.copy(alpha = 0.4f))
                )
                Text(
                    text = "Current aesthetic: ${theme.displayName.uppercase()}",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    color = colors.onSurface.copy(alpha = 0.6f),
                    letterSpacing = 1.sp
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "[ CLOSE ]",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = colors.primary,
                    letterSpacing = 2.sp
                )
            }
        }
    )
}

@Composable
private fun InstructionRow(
    number: String,
    text: String,
    textColor: androidx.compose.ui.graphics.Color,
    accentColor: androidx.compose.ui.graphics.Color
) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = number,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = accentColor
        )
        Text(
            text = text,
            fontFamily = FontFamily.Serif,
            fontSize = 13.sp,
            color = textColor,
            lineHeight = 19.sp
        )
    }
}
