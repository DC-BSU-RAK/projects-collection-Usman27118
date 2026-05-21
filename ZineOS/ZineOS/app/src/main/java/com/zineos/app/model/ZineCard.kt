package com.zineos.app.model

data class ZineCard(
    val id: Int,
    val type: CardType,
    val headline: String,
    val body: String,
    val label: String
)

enum class CardType {
    ESSAY, TYPOGRAPHY, MANIFESTO, QUOTE, FRAGMENT
}

object ZineContent {
    fun cardsForTheme(theme: ZineTheme): List<ZineCard> = when (theme) {
        ZineTheme.BAUHAUS -> bauhausCards
        ZineTheme.CYBERPUNK -> cyberpunkCards
        ZineTheme.WABI_SABI -> wabiSabiCards
        ZineTheme.BRUTALIST -> brutalistCards
        ZineTheme.SOLARPUNK -> solarpunkCards
    }

    private val bauhausCards = listOf(
        ZineCard(
            id = 1, type = CardType.MANIFESTO,
            headline = "ART + CRAFT = ONE",
            body = "The Bauhaus did not separate the painter from the carpenter. To make something beautiful was to make something useful. Every line had a reason. Every color, a function. The grid is not a cage — it is a skeleton.",
            label = "MANIFESTO / 001"
        ),
        ZineCard(
            id = 2, type = CardType.TYPOGRAPHY,
            headline = "PRIMARY\nCOLORS\nONLY",
            body = "Red. Yellow. Blue. Black. White. This is the entire universe of a certain kind of mind — the mind that believes the world becomes clearer when reduced to its essentials.",
            label = "TYPOGRAPHY / 002"
        ),
        ZineCard(
            id = 3, type = CardType.ESSAY,
            headline = "ON THE GRID",
            body = "Paul Klee taught at the Bauhaus that a line is a dot that went for a walk. Imagine, then, what a grid is: a neighborhood. A city plan. A score for eyes to move through. The grid does not oppress. It liberates by making decisions in advance.",
            label = "ESSAY / 003"
        ),
        ZineCard(
            id = 4, type = CardType.QUOTE,
            headline = "\"LESS IS MORE\"",
            body = "The paradox at the center of modernist design: subtract until you cannot subtract further, and only then will you have found what you were actually trying to say.",
            label = "FRAGMENT / 004"
        ),
        ZineCard(
            id = 5, type = CardType.ESSAY,
            headline = "WORKSHOP NOTES",
            body = "Material has memory. Wood remembers the tree. Steel remembers heat. Clay remembers hands. The honest designer does not fight this memory — they collaborate with it. The material teaches the form.",
            label = "ESSAY / 005"
        ),
        ZineCard(
            id = 6, type = CardType.FRAGMENT,
            headline = "FUNCTION\nAS\nAESTHETIC",
            body = "When a chair holds weight perfectly, it is beautiful. Not because of ornament. Because it does exactly what it promises. Promise less. Deliver fully. This is design.",
            label = "FRAGMENT / 006"
        )
    )

    private val cyberpunkCards = listOf(
        ZineCard(
            id = 1, type = CardType.MANIFESTO,
            headline = "THE NET IS ALIVE",
            body = "You are not browsing the internet. The internet is browsing you. Every click is a neuron firing in a brain that has no skull, no name, no sleep. We built the nervous system of a god and then argued about the terms of service.",
            label = "TRANSMISSION / 001"
        ),
        ZineCard(
            id = 2, type = CardType.ESSAY,
            headline = "NEON\nIS THE\nNEW BLACK",
            body = "In the year the clouds forgot to part, we learned to love artificial light. It was warmer than the sun had been. More reliable. More ours. Neon does not apologize for being seen.",
            label = "LOG / 002"
        ),
        ZineCard(
            id = 3, type = CardType.TYPOGRAPHY,
            headline = "01001100\n01001111\n01010110\n01000101",
            body = "Binary is the most intimate language. It has only two words: yes and no. And yet from these two syllables, entire worlds are dreamed into being. This is not code. This is prayer.",
            label = "DATA / 003"
        ),
        ZineCard(
            id = 4, type = CardType.FRAGMENT,
            headline = "GHOST IN THE\nSHELL SCRIPT",
            body = "Your consciousness is a process running on hardware you did not choose in a body you did not design in a world that had a 4-billion-year head start. You are already a cyborg. The chrome just hasn't shipped yet.",
            label = "FRAGMENT / 004"
        ),
        ZineCard(
            id = 5, type = CardType.ESSAY,
            headline = "CORPO NOIR",
            body = "The megacorporation is not evil. It is indifferent. This is worse. Evil at least acknowledges you. The algorithm optimizes around your existence like water around stone, leaving you dry.",
            label = "ESSAY / 005"
        ),
        ZineCard(
            id = 6, type = CardType.QUOTE,
            headline = "\"THE STREET FINDS\nITS OWN USES FOR\nTHINGS\"",
            body = "William Gibson said this in 1984 and it has been true every day since. The unofficial use of technology is always more interesting than the intended one. Hack everything. Especially yourself.",
            label = "SIGNAL / 006"
        )
    )

    private val wabiSabiCards = listOf(
        ZineCard(
            id = 1, type = CardType.ESSAY,
            headline = "ON MOSS",
            body = "Moss does not hurry. It colonizes ruins one spore at a time, patient as geologic time, unbothered by productivity metrics. A wall covered in moss is not decaying. It is being chosen again, differently.",
            label = "MEDITATION / 001"
        ),
        ZineCard(
            id = 2, type = CardType.MANIFESTO,
            headline = "THE BROKEN\nBOWL",
            body = "Kintsugi fills cracks with gold. Not to hide the break — to honor it. The break is where the history is. The break is where the light gets in. You are not damaged. You are documented.",
            label = "KINTSUGI / 002"
        ),
        ZineCard(
            id = 3, type = CardType.FRAGMENT,
            headline = "IMPERMANENCE\nAS\nPRACTICE",
            body = "The sand mandala takes monks four weeks to create. Then they sweep it into a river. The teaching is not the mandala. The teaching is the sweeping.",
            label = "FRAGMENT / 003"
        ),
        ZineCard(
            id = 4, type = CardType.ESSAY,
            headline = "AUTUMN NOTES",
            body = "There is a Japanese word — mono no aware — the pathos of things. It is the feeling of cherry blossoms falling. Not sadness, exactly. More like the ache of paying attention. Beauty is not diminished by ending. It is completed by it.",
            label = "ESSAY / 004"
        ),
        ZineCard(
            id = 5, type = CardType.TYPOGRAPHY,
            headline = "間\nMA",
            body = "The Japanese concept of Ma is the meaningful pause. The space between notes that makes music music. The silence between words that makes speech speech. Emptiness is not absence. It is potential.",
            label = "CONCEPT / 005"
        ),
        ZineCard(
            id = 6, type = CardType.QUOTE,
            headline = "\"IN THE BEGINNER'S\nMIND THERE ARE\nMANY POSSIBILITIES\"",
            body = "Shunryu Suzuki wrote this about Zen. It applies equally to design, to relationships, to Tuesday mornings. The expert's mind is a closed room. The beginner's mind is a field.",
            label = "REFLECTION / 006"
        )
    )

    private val brutalistCards = listOf(
        ZineCard(
            id = 1, type = CardType.MANIFESTO,
            headline = "RAW CONCRETE\nDOES NOT LIE",
            body = "Ornament is crime. Not because Loos said so. Because ornament is a story told over reality, and reality does not need stories. The concrete wall says: I am concrete. I am holding up this roof. That is enough.",
            label = "DOCTRINE / 001"
        ),
        ZineCard(
            id = 2, type = CardType.ESSAY,
            headline = "THE\nBRUTAL\nTRUTH",
            body = "Brutalism was never cruel. It was honest. Exposed ductwork says: heating lives here. Raw concrete says: structure lives here. The brutalist building tells you everything about itself. This is a form of respect.",
            label = "ESSAY / 002"
        ),
        ZineCard(
            id = 3, type = CardType.TYPOGRAPHY,
            headline = "WEIGHT\nMASS\nGRAVITY\nFORM",
            body = "Typography in brutalism is architecture. It takes up space. It casts shadow. It refuses to be polite. The letter is a load-bearing element.",
            label = "TYPE / 003"
        ),
        ZineCard(
            id = 4, type = CardType.FRAGMENT,
            headline = "STRUCTURE\nIS THE\nDECORATION",
            body = "Trellick Tower is beautiful not despite its exposed service core but because of it. The honest expression of how something works IS the aesthetic. There is nothing to add.",
            label = "FRAGMENT / 004"
        ),
        ZineCard(
            id = 5, type = CardType.ESSAY,
            headline = "AGAINST\nTHE POLISHED",
            body = "The finish conceals the work. Polish is a kind of lie — it says the thing has always been this smooth, this clean, this effortless. Brutalism refuses the lie. You can see the formwork marks. You can see where the concrete was poured. Evidence of making is sacred.",
            label = "ESSAY / 005"
        ),
        ZineCard(
            id = 6, type = CardType.QUOTE,
            headline = "\"BEAUTY MUST BE\nCONVULSIVE OR\nNOT AT ALL\"",
            body = "Breton said this about surrealism but it belongs to every movement that refuses comfort. The beautiful thing should shock you into presence. If you can be comfortable around it, you are not paying attention.",
            label = "SIGNAL / 006"
        )
    )

    private val solarpunkCards = listOf(
        ZineCard(
            id = 1, type = CardType.MANIFESTO,
            headline = "THE FUTURE\nIS GREEN",
            body = "Solarpunk refuses the grey future. It refuses the chrome future, the ruin future, the desert future. It insists, against all evidence, against the weight of capital and inertia, that we can grow things. That we can tend things. That the future can be something we would choose to live in.",
            label = "VISION / 001"
        ),
        ZineCard(
            id = 2, type = CardType.ESSAY,
            headline = "ON\nCOMMONS",
            body = "The commons is not naive. The tragedy of the commons was not a law of nature — it was a failure of governance. When people share resources with care and agreement, the commons thrives. The garden is tended by everyone who loves it.",
            label = "ESSAY / 002"
        ),
        ZineCard(
            id = 3, type = CardType.TYPOGRAPHY,
            headline = "MUTUAL\nAID\nNOT\nCHARITY",
            body = "Charity flows downward from abundance to lack. Mutual aid moves laterally, between people who see each other as equals. One is a gift. The other is infrastructure.",
            label = "CONCEPT / 003"
        ),
        ZineCard(
            id = 4, type = CardType.FRAGMENT,
            headline = "ROOTS ARE\nRADICAL",
            body = "Every seed is an act of faith in the future. Planting a tree you will not see mature is the most political act available. It says: there will be a future. Someone will stand in this shade. I believe in them.",
            label = "FRAGMENT / 004"
        ),
        ZineCard(
            id = 5, type = CardType.ESSAY,
            headline = "TECHNOLOGY\nSHOULD HEAL",
            body = "The question is not whether to have technology. The question is what the technology is for. A solar panel on a community center is a political statement. A drone delivering packages to a gated community is also a political statement. Choose your statements carefully.",
            label = "ESSAY / 005"
        ),
        ZineCard(
            id = 6, type = CardType.QUOTE,
            headline = "\"ANOTHER WORLD\nIS NOT ONLY POSSIBLE,\nSHE IS ON HER WAY\"",
            body = "Arundhati Roy wrote this at the beginning of the century and it remains the essential solarpunk sentence. On a quiet day, if you listen very carefully, you can hear her breathing.",
            label = "SIGNAL / 006"
        )
    )
}
