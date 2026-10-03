package com.example.model

enum class AppNavTab(val label: String, val icon: String) {
    EXPLORE("Explore", "🦁"),
    GAMES("Play & Learn", "🎮"),
    STICKERS("Stickers", "🌟"),
    SETTINGS("Parent Zone", "⚙️")
}

enum class GameType(val title: String, val subtitle: String, val icon: String, val badge: String) {
    SOUND_DETECTIVE("Sound Detective", "Who made that sound?", "👂", "3 Stars"),
    SPELLING_BEE("Letter Spelling", "Tap letters to spell!", "🔤", "5 Stars"),
    HABITAT_MATCH("Home Explorer", "Where does this pal live?", "🏡", "3 Stars")
}

data class QuizQuestion(
    val id: String,
    val soundPrompt: String,
    val textPrompt: String,
    val correctAnimal: Animal,
    val options: List<Animal>
)

data class LetterBubble(
    val id: Int,
    val char: Char,
    val isUsed: Boolean = false
)

data class HabitatQuizQuestion(
    val animal: Animal,
    val correctCategory: AnimalCategory,
    val options: List<AnimalCategory>
)
