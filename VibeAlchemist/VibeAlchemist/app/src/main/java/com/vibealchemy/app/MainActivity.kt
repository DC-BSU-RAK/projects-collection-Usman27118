package com.vibealchemy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kotlinx.coroutines.launch

// ─── Color Palette ─────────────────────────────────────────────────────────────

private val DeepVoid      = Color(0xFF0A0A0F)
private val AbyssBlue     = Color(0xFF0D0D1A)
private val NightSurface  = Color(0xFF12121F)
private val CardSurface   = Color(0xFF1A1A2E)
private val RimLight      = Color(0xFF2A2A4A)

private val NeonPurple    = Color(0xFFC084FC)
private val ElectricPink  = Color(0xFFF472B6)
private val CyberCyan     = Color(0xFF67E8F9)
private val GoldGlow      = Color(0xFFFBBF24)
private val SageGlow      = Color(0xFF86EFAC)
private val CoralFlare    = Color(0xFFFB7185)
private val IndigoArc     = Color(0xFF818CF8)

private val TextPrimary   = Color(0xFFF0E6FF)
private val TextSecondary = Color(0xFFAA99CC)
private val TextDim       = Color(0xFF665580)

// ─── Vibe Concepts ─────────────────────────────────────────────────────────────

data class VibeConcept(
    val label: String,
    val primaryColor: Color,
    val secondaryColor: Color,
    val emoji: String
)

val vibeConceptMap = mapOf(
    "Neon"       to VibeConcept("Neon",       Color(0xFF00FFFF), Color(0xFF7B2FBE), "⚡"),
    "Velvet"     to VibeConcept("Velvet",     Color(0xFF9B2D7A), Color(0xFF2D0A4E), "🌹"),
    "Retro"      to VibeConcept("Retro",      Color(0xFFFF6B35), Color(0xFF2B1500), "📼"),
    "Melancholy" to VibeConcept("Melancholy", Color(0xFF4A90D9), Color(0xFF0A1628), "🌧️"),
    "Static"     to VibeConcept("Static",     Color(0xFFB0B0B0), Color(0xFF1A1A1A), "📡"),
    "Euphoria"   to VibeConcept("Euphoria",   Color(0xFFFFD700), Color(0xFF7B3F00), "✨"),
    "Geometric"  to VibeConcept("Geometric",  Color(0xFF7FFFD4), Color(0xFF003333), "◈")
)

// ─── Alchemy Engine ────────────────────────────────────────────────────────────

data class AlchemyResult(
    val phrase: String,
    val gradientStart: Color,
    val gradientMid: Color,
    val gradientEnd: Color
)

fun transmute(conceptA: String, operator: String, conceptB: String): AlchemyResult {
    val a = vibeConceptMap[conceptA]
    val b = vibeConceptMap[conceptB]
    val gradStart = a?.primaryColor ?: NeonPurple
    val gradMid   = b?.primaryColor ?: ElectricPink
    val gradEnd   = a?.secondaryColor ?: AbyssBlue

    val phrase = alchemyPhraseTable[Triple(conceptA, operator, conceptB)]
        ?: alchemyPhraseTable[Triple(conceptB, operator, conceptA)]
        ?: generateDefaultPhrase(conceptA, operator, conceptB)

    return AlchemyResult(phrase, gradStart, gradMid, gradEnd)
}

fun generateDefaultPhrase(a: String, op: String, b: String): String {
    val verb = when (op) {
        "+" -> "merges with"
        "×" -> "multiplies into"
        else -> "becomes"
    }
    val endings = listOf(
        "a sensation without a name",
        "something the universe invented just now",
        "a frequency only the soul can tune to",
        "the color between sleeping and waking",
        "a memory that never happened"
    )
    return "$a $verb $b —\n${endings.random()}"
}

val alchemyPhraseTable: Map<Triple<String, String, String>, String> = mapOf(
    // NEON combinations
    Triple("Neon", "+", "Melancholy")   to "Neon + Melancholy =\na glowing sadness\nin the digital rain",
    Triple("Neon", "+", "Velvet")       to "Neon + Velvet =\nthe city at 3am\nthrough rose-stained glass",
    Triple("Neon", "+", "Retro")        to "Neon + Retro =\na dream you had\nin 1987",
    Triple("Neon", "+", "Static")       to "Neon + Static =\nbroken signals\nthat glow anyway",
    Triple("Neon", "+", "Euphoria")     to "Neon + Euphoria =\na thousand suns\ncompressed into a moment",
    Triple("Neon", "+", "Geometric")    to "Neon + Geometric =\nangles that hum\nwith electric intention",
    Triple("Neon", "×", "Melancholy")   to "Neon × Melancholy =\nan infinite corridor\nlit by forgotten stars",
    Triple("Neon", "×", "Velvet")       to "Neon × Velvet =\nsoft thunder\nin a neon cathedral",
    Triple("Neon", "×", "Static")       to "Neon × Static =\nthe scream of signals\nbefore they find their words",
    Triple("Neon", "×", "Euphoria")     to "Neon × Euphoria =\nrapture rendered\nin ultraviolet",
    Triple("Neon", "×", "Geometric")    to "Neon × Geometric =\nthe architecture\nof a perfect mind",
    // VELVET combinations
    Triple("Velvet", "+", "Melancholy") to "Velvet + Melancholy =\na slow waltz\nfor no one in the hall",
    Triple("Velvet", "+", "Retro")      to "Velvet + Retro =\ngrandmother's perfume\nin an empty cinema",
    Triple("Velvet", "+", "Static")     to "Velvet + Static =\na ghost of a song\nhalf-remembered",
    Triple("Velvet", "+", "Euphoria")   to "Velvet + Euphoria =\nthe warmth\nyou didn't know you needed",
    Triple("Velvet", "+", "Geometric")  to "Velvet + Geometric =\nthe mathematics\nof a caress",
    Triple("Velvet", "×", "Melancholy") to "Velvet × Melancholy =\ndarkness that\nwants to be held",
    Triple("Velvet", "×", "Retro")      to "Velvet × Retro =\na love letter\nwritten in smoke",
    Triple("Velvet", "×", "Euphoria")   to "Velvet × Euphoria =\nbliss with\na soft edge",
    // RETRO combinations
    Triple("Retro", "+", "Melancholy")  to "Retro + Melancholy =\nold photographs\nin a sunlit drawer",
    Triple("Retro", "+", "Static")      to "Retro + Static =\nthe sound of something\nalmost remembered",
    Triple("Retro", "+", "Euphoria")    to "Retro + Euphoria =\nnostalgia sharp enough\nto make you laugh",
    Triple("Retro", "+", "Geometric")   to "Retro + Geometric =\na blueprint\nfor a city that chose joy",
    Triple("Retro", "×", "Melancholy")  to "Retro × Melancholy =\na sepia infinity\nfor feelings without origin",
    Triple("Retro", "×", "Static")      to "Retro × Static =\na broadcast\nfrom a self you outgrew",
    Triple("Retro", "×", "Euphoria")    to "Retro × Euphoria =\nthe golden hour\nthat never ended",
    // MELANCHOLY combinations
    Triple("Melancholy", "+", "Static") to "Melancholy + Static =\nthe channel between\nguilt and sleep",
    Triple("Melancholy", "+", "Euphoria") to "Melancholy + Euphoria =\nthe bittersweet\ntaste of almost",
    Triple("Melancholy", "+", "Geometric") to "Melancholy + Geometric =\nthe precise shape\nof a missing thing",
    Triple("Melancholy", "×", "Static") to "Melancholy × Static =\nthe silence that\nhums with grief",
    Triple("Melancholy", "×", "Euphoria") to "Melancholy × Euphoria =\na joy so full\nit aches",
    Triple("Melancholy", "×", "Geometric") to "Melancholy × Geometric =\nan infinite grid\nof beautiful losses",
    // STATIC combinations
    Triple("Static", "+", "Euphoria")   to "Static + Euphoria =\ninterference patterns\nof pure delight",
    Triple("Static", "+", "Geometric")  to "Static + Geometric =\nthe noise beneath\nall order",
    Triple("Static", "×", "Euphoria")   to "Static × Euphoria =\nfragmented bliss —\nmore real for being broken",
    Triple("Static", "×", "Geometric")  to "Static × Geometric =\nfractal chaos\npretending to be a map",
    // EUPHORIA combinations
    Triple("Euphoria", "+", "Geometric") to "Euphoria + Geometric =\na temple built\nfor the feeling of yes",
    Triple("Euphoria", "×", "Geometric") to "Euphoria × Geometric =\nthe crystalline\nstructure of joy",
    // SELF combinations
    Triple("Neon", "+", "Neon")         to "Neon + Neon =\nblinding clarity —\ntoo bright to look at",
    Triple("Velvet", "+", "Velvet")     to "Velvet + Velvet =\nan abyss\nthat feels like home",
    Triple("Retro", "+", "Retro")       to "Retro + Retro =\na loop of longing\nspinning forever",
    Triple("Melancholy", "+", "Melancholy") to "Melancholy + Melancholy =\nthe ocean\nweeping for itself",
    Triple("Static", "+", "Static")     to "Static + Static =\nnoise that\nbecomes a new language",
    Triple("Euphoria", "+", "Euphoria") to "Euphoria + Euphoria =\nan impossible warmth —\nyou are not ready",
    Triple("Geometric", "+", "Geometric") to "Geometric + Geometric =\na dimension folding\ninto its own logic"
)

// ─── App State ─────────────────────────────────────────────────────────────────

data class AlchemistState(
    val displayText: String = "∿",
    val subText: String = "select a vibe to begin",
    val conceptA: String = "",
    val operator: String = "",
    val conceptB: String = "",
    val result: AlchemyResult? = null,
    val inputSequence: String = ""
)

// ─── Theme ─────────────────────────────────────────────────────────────────────

private val AlchemistColorScheme = darkColorScheme(
    primary        = NeonPurple,
    onPrimary      = Color.Black,
    secondary      = ElectricPink,
    onSecondary    = Color.Black,
    tertiary       = CyberCyan,
    background     = DeepVoid,
    onBackground   = TextPrimary,
    surface        = CardSurface,
    onSurface      = TextPrimary,
    surfaceVariant = NightSurface,
    outline        = RimLight
)

private val AlchemistTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Black,
        fontSize = 28.sp,
        letterSpacing = (-0.5).sp,
        color = TextPrimary
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        letterSpacing = 1.5.sp
    )
)

// ─── Main Activity ──────────────────────────────────────────────────────────────

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(
                colorScheme = AlchemistColorScheme,
                typography  = AlchemistTypography
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color    = DeepVoid
                ) {
                    VibeAlchemistScreen()
                }
            }
        }
    }
}

// ─── Root Screen ───────────────────────────────────────────────────────────────

@Composable
fun VibeAlchemistScreen() {
    var state         by remember { mutableStateOf(AlchemistState()) }
    var showGuide     by remember { mutableStateOf(false) }

    if (showGuide) {
        AlchemyGuideDialog(onDismiss = { showGuide = false })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepVoid)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // ── Header ──
        AppHeader(onGuideClick = { showGuide = true })

        // ── Output Display ──
        OutputDisplay(state = state)

        // ── Input Echo ──
        InputEchoBar(state = state)

        // ── Calculator Grid ──
        CalculatorGrid(
            state    = state,
            onInput  = { token -> state = handleInput(state, token) }
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

// ─── Input Logic ───────────────────────────────────────────────────────────────

fun handleInput(state: AlchemistState, token: String): AlchemistState {
    return when {
        token == "C" -> AlchemistState()

        token == "=" -> {
            if (state.conceptA.isNotEmpty() && state.operator.isNotEmpty() && state.conceptB.isNotEmpty()) {
                val result = transmute(state.conceptA, state.operator, state.conceptB)
                state.copy(
                    displayText   = result.phrase,
                    subText       = "${state.conceptA} ${state.operator} ${state.conceptB}",
                    result        = result,
                    inputSequence = "${state.conceptA} ${state.operator} ${state.conceptB} ="
                )
            } else {
                state.copy(subText = "incomplete formula…")
            }
        }

        token in listOf("+", "×") -> {
            if (state.conceptA.isNotEmpty() && state.result == null) {
                state.copy(
                    operator      = token,
                    inputSequence = "${state.conceptA} $token"
                )
            } else state
        }

        token in vibeConceptMap.keys -> {
            when {
                state.conceptA.isEmpty() -> state.copy(
                    conceptA      = token,
                    displayText   = "∿",
                    subText       = token,
                    inputSequence = token
                )
                state.operator.isNotEmpty() && state.conceptB.isEmpty() -> state.copy(
                    conceptB      = token,
                    inputSequence = "${state.conceptA} ${state.operator} $token"
                )
                else -> state
            }
        }

        else -> state
    }
}

// ─── App Header ────────────────────────────────────────────────────────────────

@Composable
fun AppHeader(onGuideClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text  = "VIBE",
                style = TextStyle(
                    fontSize     = 10.sp,
                    fontWeight   = FontWeight.Bold,
                    letterSpacing = 6.sp,
                    color        = NeonPurple
                )
            )
            Text(
                text  = "ALCHEMIST",
                style = TextStyle(
                    fontSize     = 22.sp,
                    fontWeight   = FontWeight.Black,
                    letterSpacing = (-0.5).sp,
                    color        = TextPrimary
                )
            )
        }

        // Guide button
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(NeonPurple.copy(alpha = 0.3f), Color.Transparent)
                    )
                )
                .border(1.dp, NeonPurple.copy(alpha = 0.6f), CircleShape)
                .clickable(onClick = onGuideClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text  = "?",
                style = TextStyle(
                    fontSize   = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color      = NeonPurple
                )
            )
        }
    }
}

// ─── Output Display ────────────────────────────────────────────────────────────

@Composable
fun OutputDisplay(state: AlchemistState) {
    val targetStart by animateColorAsState(
        targetValue    = state.result?.gradientStart ?: NeonPurple.copy(alpha = 0.15f),
        animationSpec  = tween(800),
        label          = "gradStart"
    )
    val targetMid by animateColorAsState(
        targetValue   = state.result?.gradientMid ?: ElectricPink.copy(alpha = 0.08f),
        animationSpec = tween(900),
        label         = "gradMid"
    )
    val targetEnd by animateColorAsState(
        targetValue   = state.result?.gradientEnd ?: AbyssBlue,
        animationSpec = tween(1000),
        label         = "gradEnd"
    )

    val textColor = if (state.result != null) {
        val bg = targetStart
        if (bg.luminance() > 0.3f) Color.Black else Color.White
    } else TextPrimary

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        targetStart.copy(alpha = 0.35f),
                        targetMid.copy(alpha = 0.2f),
                        targetEnd.copy(alpha = 0.6f)
                    )
                )
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        NeonPurple.copy(alpha = 0.5f),
                        ElectricPink.copy(alpha = 0.3f)
                    )
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (state.result != null) {
                Text(
                    text      = state.displayText,
                    style     = TextStyle(
                        fontSize    = 19.sp,
                        fontWeight  = FontWeight.Bold,
                        lineHeight  = 28.sp,
                        textAlign   = TextAlign.Center,
                        color       = textColor
                    )
                )
            } else {
                Text(
                    text  = state.displayText,
                    style = TextStyle(
                        fontSize   = 52.sp,
                        fontWeight = FontWeight.Thin,
                        color      = NeonPurple.copy(alpha = 0.7f)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text  = state.subText,
                    style = TextStyle(
                        fontSize      = 13.sp,
                        color         = TextSecondary,
                        letterSpacing = 1.sp
                    )
                )
            }
        }

        // Corner sigil
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
        ) {
            Text(
                text  = "◈",
                style = TextStyle(
                    fontSize = 14.sp,
                    color    = NeonPurple.copy(alpha = 0.4f)
                )
            )
        }
    }
}

// ─── Input Echo Bar ────────────────────────────────────────────────────────────

@Composable
fun InputEchoBar(state: AlchemistState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(NightSurface),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text     = if (state.inputSequence.isNotEmpty()) state.inputSequence else "—",
            modifier = Modifier.padding(horizontal = 16.dp),
            style    = TextStyle(
                fontSize      = 13.sp,
                color         = TextSecondary,
                letterSpacing = 1.sp,
                fontWeight    = FontWeight.Medium
            ),
            maxLines = 1
        )
    }
}

// ─── Calculator Grid ───────────────────────────────────────────────────────────

@Composable
fun CalculatorGrid(
    state   : AlchemistState,
    onInput : (String) -> Unit
) {
    val vibeButtons = listOf("Neon", "Velvet", "Retro", "Melancholy", "Static", "Euphoria", "Geometric")
    val operators   = listOf("+", "×")

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // ── Vibe concept grid (3-column) ──
        val rows = vibeButtons.chunked(3)
        rows.forEachIndexed { rowIdx, row ->
            Row(
                modifier            = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                row.forEach { concept ->
                    val vibe = vibeConceptMap[concept]!!
                    VibeButton(
                        label          = concept,
                        emoji          = vibe.emoji,
                        primaryColor   = vibe.primaryColor,
                        secondaryColor = vibe.secondaryColor,
                        isActive       = (state.conceptA == concept || state.conceptB == concept),
                        modifier       = Modifier.weight(1f),
                        onClick        = { onInput(concept) }
                    )
                }
                // Fill last row if incomplete
                if (rowIdx == rows.lastIndex && row.size < 3) {
                    repeat(3 - row.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // ── Operator row + Clear + Equals ──
        Row(
            modifier              = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Operators
            operators.forEach { op ->
                OperatorButton(
                    label    = op,
                    isActive = state.operator == op,
                    modifier = Modifier.weight(1f),
                    onClick  = { onInput(op) }
                )
            }

            // Clear
            ActionButton(
                label    = "C",
                color    = CoralFlare,
                modifier = Modifier.weight(1f),
                onClick  = { onInput("C") }
            )

            // Equals
            ActionButton(
                label    = "=",
                color    = NeonPurple,
                modifier = Modifier.weight(1.5f),
                onClick  = { onInput("=") }
            )
        }
    }
}

// ─── Button Components ─────────────────────────────────────────────────────────

@Composable
fun VibeButton(
    label          : String,
    emoji          : String,
    primaryColor   : Color,
    secondaryColor : Color,
    isActive       : Boolean,
    modifier       : Modifier = Modifier,
    onClick        : () -> Unit
) {
    val scope     = rememberCoroutineScope()
    val scale     = remember { Animatable(1f) }
    val interactionSource = remember { MutableInteractionSource() }

    val borderAlpha by animateFloatAsState(
        targetValue   = if (isActive) 0.9f else 0.25f,
        animationSpec = tween(300),
        label         = "borderAlpha"
    )
    val bgAlpha by animateFloatAsState(
        targetValue   = if (isActive) 0.35f else 0.12f,
        animationSpec = tween(300),
        label         = "bgAlpha"
    )

    Box(
        modifier = modifier
            .height(72.dp)
            .scale(scale.value)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        primaryColor.copy(alpha = bgAlpha),
                        secondaryColor.copy(alpha = bgAlpha * 0.5f)
                    )
                )
            )
            .border(
                width = if (isActive) 1.5.dp else 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        primaryColor.copy(alpha = borderAlpha),
                        primaryColor.copy(alpha = borderAlpha * 0.4f)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication        = null
            ) {
                scope.launch {
                    scale.animateTo(0.92f, spring(stiffness = Spring.StiffnessHigh))
                    scale.animateTo(1f,    spring(stiffness = Spring.StiffnessMediumLow))
                }
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text  = emoji,
                style = TextStyle(fontSize = 20.sp)
            )
            Text(
                text  = label,
                style = TextStyle(
                    fontSize      = 11.sp,
                    fontWeight    = FontWeight.SemiBold,
                    color         = if (isActive) primaryColor else primaryColor.copy(alpha = 0.75f),
                    letterSpacing = 0.5.sp
                )
            )
        }
    }
}

@Composable
fun OperatorButton(
    label    : String,
    isActive : Boolean,
    modifier : Modifier = Modifier,
    onClick  : () -> Unit
) {
    val scope = rememberCoroutineScope()
    val scale = remember { Animatable(1f) }
    val interactionSource = remember { MutableInteractionSource() }

    val bgAlpha by animateFloatAsState(
        targetValue   = if (isActive) 0.3f else 0.08f,
        animationSpec = tween(300),
        label         = "opBg"
    )

    Box(
        modifier = modifier
            .height(56.dp)
            .scale(scale.value)
            .clip(RoundedCornerShape(14.dp))
            .background(GoldGlow.copy(alpha = bgAlpha))
            .border(
                width = 1.dp,
                color = GoldGlow.copy(alpha = if (isActive) 0.8f else 0.3f),
                shape = RoundedCornerShape(14.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication        = null
            ) {
                scope.launch {
                    scale.animateTo(0.9f, spring(stiffness = Spring.StiffnessHigh))
                    scale.animateTo(1f,   spring(stiffness = Spring.StiffnessMediumLow))
                }
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text  = label,
            style = TextStyle(
                fontSize   = 22.sp,
                fontWeight = FontWeight.Bold,
                color      = GoldGlow
            )
        )
    }
}

@Composable
fun ActionButton(
    label    : String,
    color    : Color,
    modifier : Modifier = Modifier,
    onClick  : () -> Unit
) {
    val scope = rememberCoroutineScope()
    val scale = remember { Animatable(1f) }
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .height(56.dp)
            .scale(scale.value)
            .clip(RoundedCornerShape(14.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(color.copy(alpha = 0.25f), color.copy(alpha = 0.1f))
                )
            )
            .border(1.5.dp, color.copy(alpha = 0.7f), RoundedCornerShape(14.dp))
            .clickable(
                interactionSource = interactionSource,
                indication        = null
            ) {
                scope.launch {
                    scale.animateTo(0.9f, spring(stiffness = Spring.StiffnessHigh))
                    scale.animateTo(1f,   spring(stiffness = Spring.StiffnessMediumLow))
                }
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text  = label,
            style = TextStyle(
                fontSize   = if (label == "=") 26.sp else 18.sp,
                fontWeight = FontWeight.Bold,
                color      = color
            )
        )
    }
}

// ─── Alchemy Guide Dialog ──────────────────────────────────────────────────────

@Composable
fun AlchemyGuideDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest  = onDismiss,
        properties        = DialogProperties(usePlatformDefaultWidth = false),
        modifier          = Modifier
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(CardSurface, Color(0xFF1A0A2E))
                )
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(NeonPurple.copy(alpha = 0.5f), ElectricPink.copy(alpha = 0.3f))
                ),
                shape = RoundedCornerShape(28.dp)
            ),
        containerColor = Color.Transparent,
        title = {
            Column {
                Text(
                    text  = "◈  ALCHEMY GUIDE",
                    style = TextStyle(
                        fontSize      = 13.sp,
                        letterSpacing = 3.sp,
                        fontWeight    = FontWeight.Bold,
                        color         = NeonPurple
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text  = "How to Transmute Vibes",
                    style = TextStyle(
                        fontSize   = 20.sp,
                        fontWeight = FontWeight.Black,
                        color      = TextPrimary
                    )
                )
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {

                GuideStep(
                    number = "01",
                    color  = NeonPurple,
                    title  = "Select a Vibe",
                    body   = "Tap any concept button — Neon, Velvet, Retro, Melancholy, Static, Euphoria, or Geometric — to set your first ingredient."
                )
                GuideStep(
                    number = "02",
                    color  = GoldGlow,
                    title  = "Choose an Operator",
                    body   = "Tap + to blend or × to amplify. Each operator changes the nature of the transmutation."
                )
                GuideStep(
                    number = "03",
                    color  = ElectricPink,
                    title  = "Add the Second Vibe",
                    body   = "Select your second concept. You can even combine the same vibe with itself for unexpected results."
                )
                GuideStep(
                    number = "04",
                    color  = CyberCyan,
                    title  = "Press = to Transmute",
                    body   = "The output display transforms — color, gradient, and a unique poetic phrase are generated from your formula."
                )
                GuideStep(
                    number = "C",
                    color  = CoralFlare,
                    title  = "Clear the Canvas",
                    body   = "Reset everything and begin a new alchemical experiment."
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(NeonPurple.copy(alpha = 0.08f))
                        .border(1.dp, NeonPurple.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text  = "\"There are no wrong combinations. Every formula reveals a truth the numbers never could.\"",
                        style = TextStyle(
                            fontSize   = 12.sp,
                            color      = TextSecondary,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 20.sp
                        )
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text  = "BEGIN TRANSMUTING",
                    style = TextStyle(
                        fontSize      = 12.sp,
                        fontWeight    = FontWeight.Bold,
                        letterSpacing = 1.5.sp,
                        color         = NeonPurple
                    )
                )
            }
        }
    )
}

@Composable
fun GuideStep(
    number : String,
    color  : Color,
    title  : String,
    body   : String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment     = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.15f))
                .border(1.dp, color.copy(alpha = 0.5f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text  = number,
                style = TextStyle(
                    fontSize   = 10.sp,
                    fontWeight = FontWeight.Black,
                    color      = color
                )
            )
        }
        Column {
            Text(
                text  = title,
                style = TextStyle(
                    fontSize   = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color      = TextPrimary
                )
            )
            Text(
                text  = body,
                style = TextStyle(
                    fontSize   = 12.sp,
                    color      = TextSecondary,
                    lineHeight = 18.sp
                )
            )
        }
    }
}
