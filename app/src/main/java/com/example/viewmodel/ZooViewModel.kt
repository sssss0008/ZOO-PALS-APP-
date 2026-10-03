package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.TtsHelper
import com.example.data.AnimalData
import com.example.data.UserProgressManager
import com.example.model.Animal
import com.example.model.AnimalCategory
import com.example.model.AppNavTab
import com.example.model.GameType
import com.example.model.HabitatQuizQuestion
import com.example.model.LetterBubble
import com.example.model.QuizQuestion
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class ZooViewModel(application: Application) : AndroidViewModel(application) {

    private val progressManager = UserProgressManager(application)
    val tts = TtsHelper(application)

    private val _currentNavTab = MutableStateFlow(AppNavTab.EXPLORE)
    val currentNavTab: StateFlow<AppNavTab> = _currentNavTab.asStateFlow()

    private val _selectedCategory = MutableStateFlow(AnimalCategory.ALL)
    val selectedCategory: StateFlow<AnimalCategory> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedLetterFilter = MutableStateFlow<Char?>(null)
    val selectedLetterFilter: StateFlow<Char?> = _selectedLetterFilter.asStateFlow()

    private val _selectedAnimal = MutableStateFlow<Animal?>(null)
    val selectedAnimal: StateFlow<Animal?> = _selectedAnimal.asStateFlow()

    private val _favorites = MutableStateFlow(progressManager.getFavoriteIds())
    val favorites: StateFlow<Set<String>> = _favorites.asStateFlow()

    private val _unlockedStickers = MutableStateFlow(progressManager.getUnlockedStickers())
    val unlockedStickers: StateFlow<Set<String>> = _unlockedStickers.asStateFlow()

    private val _stars = MutableStateFlow(progressManager.getTotalStars())
    val stars: StateFlow<Int> = _stars.asStateFlow()

    private val _isSlowSpeech = MutableStateFlow(progressManager.isSlowSpeech())
    val isSlowSpeech: StateFlow<Boolean> = _isSlowSpeech.asStateFlow()

    private val _isSoundEnabled = MutableStateFlow(progressManager.isSoundEnabled())
    val isSoundEnabled: StateFlow<Boolean> = _isSoundEnabled.asStateFlow()

    private val _showCelebration = MutableStateFlow(false)
    val showCelebration: StateFlow<Boolean> = _showCelebration.asStateFlow()

    private val _celebrationText = MutableStateFlow("")
    val celebrationText: StateFlow<String> = _celebrationText.asStateFlow()

    // Active Game
    private val _activeGame = MutableStateFlow<GameType?>(null)
    val activeGame: StateFlow<GameType?> = _activeGame.asStateFlow()

    // Sound Quiz
    private val _soundQuizQuestion = MutableStateFlow<QuizQuestion?>(null)
    val soundQuizQuestion: StateFlow<QuizQuestion?> = _soundQuizQuestion.asStateFlow()

    private val _soundQuizFeedback = MutableStateFlow<String?>(null)
    val soundQuizFeedback: StateFlow<String?> = _soundQuizFeedback.asStateFlow()

    // Spelling Bee
    private val _spellingAnimal = MutableStateFlow<Animal?>(null)
    val spellingAnimal: StateFlow<Animal?> = _spellingAnimal.asStateFlow()

    private val _scrambledBubbles = MutableStateFlow<List<LetterBubble>>(emptyList())
    val scrambledBubbles: StateFlow<List<LetterBubble>> = _scrambledBubbles.asStateFlow()

    private val _placedBubbles = MutableStateFlow<List<LetterBubble>>(emptyList())
    val placedBubbles: StateFlow<List<LetterBubble>> = _placedBubbles.asStateFlow()

    private val _spellingSuccess = MutableStateFlow(false)
    val spellingSuccess: StateFlow<Boolean> = _spellingSuccess.asStateFlow()

    // Habitat Quiz
    private val _habitatQuizQuestion = MutableStateFlow<HabitatQuizQuestion?>(null)
    val habitatQuizQuestion: StateFlow<HabitatQuizQuestion?> = _habitatQuizQuestion.asStateFlow()

    private val _habitatFeedback = MutableStateFlow<String?>(null)
    val habitatFeedback: StateFlow<String?> = _habitatFeedback.asStateFlow()

    // Animal of the day
    val animalOfTheDay: Animal by lazy {
        val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        AnimalData.animals[dayOfYear % AnimalData.animals.size]
    }

    init {
        tts.setSlowMode(_isSlowSpeech.value)
        tts.setSoundEnabled(_isSoundEnabled.value)
    }

    fun setNavTab(tab: AppNavTab) {
        _currentNavTab.value = tab
        if (tab != AppNavTab.GAMES) {
            _activeGame.value = null
        }
    }

    fun selectCategory(category: AnimalCategory) {
        _selectedCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setLetterFilter(letter: Char?) {
        _selectedLetterFilter.value = letter
    }

    fun openAnimalDetail(animal: Animal) {
        _selectedAnimal.value = animal
        // Speak animal name automatically when opened
        tts.speak(animal.name)
    }

    fun closeAnimalDetail() {
        _selectedAnimal.value = null
        tts.stop()
    }

    fun toggleFavorite(animalId: String) {
        val updated = progressManager.toggleFavorite(animalId)
        _favorites.value = progressManager.getFavoriteIds()
        if (updated) {
            triggerConfetti("Favorited! ⭐")
        }
    }

    fun isFavorite(animalId: String): Boolean = _favorites.value.contains(animalId)

    fun playAnimalName(animal: Animal) {
        tts.speak(animal.name)
    }

    fun playAnimalSound(animal: Animal) {
        tts.speakAnimalSound(animal.name, animal.soundEffect)
    }

    fun playPhonics(animal: Animal) {
        tts.speakPhonics(animal.name)
    }

    fun speakText(text: String) {
        tts.speak(text)
    }

    fun setSlowSpeech(enabled: Boolean) {
        _isSlowSpeech.value = enabled
        progressManager.setSlowSpeech(enabled)
        tts.setSlowMode(enabled)
        if (enabled) {
            tts.speak("Slow friendly voice turned on!")
        } else {
            tts.speak("Normal voice ready!")
        }
    }

    fun toggleSound() {
        val newState = !_isSoundEnabled.value
        _isSoundEnabled.value = newState
        progressManager.setSoundEnabled(newState)
        tts.setSoundEnabled(newState)
    }

    fun triggerConfetti(message: String) {
        viewModelScope.launch {
            _celebrationText.value = message
            _showCelebration.value = true
            delay(2500)
            _showCelebration.value = false
        }
    }

    // --- GAMES ---

    fun openGame(game: GameType) {
        _activeGame.value = game
        when (game) {
            GameType.SOUND_DETECTIVE -> nextSoundDetectiveQuestion()
            GameType.SPELLING_BEE -> nextSpellingBee()
            GameType.HABITAT_MATCH -> nextHabitatQuestion()
        }
    }

    fun exitGame() {
        _activeGame.value = null
        _soundQuizQuestion.value = null
        _soundQuizFeedback.value = null
        _spellingAnimal.value = null
        _habitatQuizQuestion.value = null
        _habitatFeedback.value = null
        tts.stop()
    }

    // Sound Detective
    fun nextSoundDetectiveQuestion() {
        _soundQuizFeedback.value = null
        val target = AnimalData.animals.random()
        val others = AnimalData.animals.filter { it.id != target.id }.shuffled().take(2)
        val options = (others + target).shuffled()

        _soundQuizQuestion.value = QuizQuestion(
            id = target.id,
            soundPrompt = target.soundEffect,
            textPrompt = "Listen carefully! Who says: \"${target.soundEffect}\"?",
            correctAnimal = target,
            options = options
        )

        // Speak the sound
        viewModelScope.launch {
            delay(300)
            tts.speak("Listen! Who says: ${target.soundEffect}?")
        }
    }

    fun answerSoundQuestion(selected: Animal) {
        val question = _soundQuizQuestion.value ?: return
        if (_soundQuizFeedback.value != null) return // Already answered

        if (selected.id == question.correctAnimal.id) {
            _soundQuizFeedback.value = "CORRECT"
            awardStars(3)
            progressManager.unlockSticker(selected.id)
            _unlockedStickers.value = progressManager.getUnlockedStickers()
            triggerConfetti("Brilliant! +3 Stars ⭐")
            tts.speakCelebration("Yes! The ${selected.name} says ${selected.soundEffect}! You won 3 stars!")
        } else {
            _soundQuizFeedback.value = "WRONG"
            tts.speakEncouragement()
        }
    }

    // Spelling Bee
    fun nextSpellingBee() {
        val animal = AnimalData.animals.filter { it.name.length in 3..6 }.random()
        _spellingAnimal.value = animal
        _placedBubbles.value = emptyList()
        _spellingSuccess.value = false

        val bubbles = animal.name.uppercase().mapIndexed { index, char ->
            LetterBubble(id = index, char = char, isUsed = false)
        }.shuffled()

        _scrambledBubbles.value = bubbles

        viewModelScope.launch {
            delay(300)
            tts.speak("Let's spell ${animal.name}! Tap the letters in order.")
        }
    }

    fun tapLetterBubble(bubble: LetterBubble) {
        if (bubble.isUsed || _spellingSuccess.value) return
        val currentPlaced = _placedBubbles.value.toMutableList()
        currentPlaced.add(bubble)
        _placedBubbles.value = currentPlaced

        // Mark bubble used
        _scrambledBubbles.value = _scrambledBubbles.value.map {
            if (it.id == bubble.id) it.copy(isUsed = true) else it
        }

        tts.speak(bubble.char.toString())

        // Check if finished
        val target = _spellingAnimal.value?.name?.uppercase() ?: return
        val currentWord = currentPlaced.map { it.char }.joinToString("")
        if (currentWord.length == target.length) {
            if (currentWord == target) {
                // Success!
                _spellingSuccess.value = true
                awardStars(5)
                val animal = _spellingAnimal.value!!
                progressManager.unlockSticker(animal.id)
                _unlockedStickers.value = progressManager.getUnlockedStickers()
                triggerConfetti("Spelling Master! +5 Stars ⭐")
                tts.speakCelebration("Hooray! You spelled ${animal.name}! Fantastic job!")
            } else {
                // Wrong spelling, shake and reset placed
                viewModelScope.launch {
                    delay(500)
                    tts.speak("Almost! Let's try spelling again.")
                    delay(500)
                    resetSpellingLetters()
                }
            }
        }
    }

    fun removePlacedLetter(index: Int) {
        if (_spellingSuccess.value) return
        val current = _placedBubbles.value.toMutableList()
        if (index in current.indices) {
            val removed = current.removeAt(index)
            _placedBubbles.value = current
            // Make un-used
            _scrambledBubbles.value = _scrambledBubbles.value.map {
                if (it.id == removed.id) it.copy(isUsed = false) else it
            }
        }
    }

    fun resetSpellingLetters() {
        _placedBubbles.value = emptyList()
        _scrambledBubbles.value = _scrambledBubbles.value.map { it.copy(isUsed = false) }
    }

    // Habitat Match
    fun nextHabitatQuestion() {
        _habitatFeedback.value = null
        val animal = AnimalData.animals.filter { it.category != AnimalCategory.ALL }.random()
        val allCats = listOf(
            AnimalCategory.SAFARI,
            AnimalCategory.FARM,
            AnimalCategory.OCEAN,
            AnimalCategory.BIRDS,
            AnimalCategory.FOREST
        )
        val options = (listOf(animal.category) + (allCats - animal.category).shuffled().take(2)).shuffled()

        _habitatQuizQuestion.value = HabitatQuizQuestion(
            animal = animal,
            correctCategory = animal.category,
            options = options
        )

        viewModelScope.launch {
            delay(300)
            tts.speak("Where does the ${animal.name} live?")
        }
    }

    fun answerHabitatQuestion(selected: AnimalCategory) {
        val question = _habitatQuizQuestion.value ?: return
        if (_habitatFeedback.value != null) return

        if (selected == question.correctCategory) {
            _habitatFeedback.value = "CORRECT"
            awardStars(3)
            progressManager.unlockSticker(question.animal.id)
            _unlockedStickers.value = progressManager.getUnlockedStickers()
            triggerConfetti("Habitat Champion! +3 Stars ⭐")
            tts.speakCelebration("Correct! The ${question.animal.name} lives in the ${selected.displayName}! +3 Stars!")
        } else {
            _habitatFeedback.value = "WRONG"
            tts.speakEncouragement()
        }
    }

    fun unlockStickerWithStars(animalId: String, cost: Int = 5): Boolean {
        if (progressManager.spendStars(cost)) {
            progressManager.unlockSticker(animalId)
            _stars.value = progressManager.getTotalStars()
            _unlockedStickers.value = progressManager.getUnlockedStickers()
            val animal = AnimalData.getAnimalById(animalId)
            triggerConfetti("Sticker Unlocked! 🌟")
            tts.speakCelebration("You unlocked the ${animal?.name ?: "animal"} sticker!")
            return true
        } else {
            tts.speak("You need $cost stars! Play quizzes to earn more!")
            return false
        }
    }

    private fun awardStars(amount: Int) {
        progressManager.addStars(amount)
        _stars.value = progressManager.getTotalStars()
    }

    fun resetProgress() {
        progressManager.resetAllProgress()
        _stars.value = progressManager.getTotalStars()
        _favorites.value = progressManager.getFavoriteIds()
        _unlockedStickers.value = progressManager.getUnlockedStickers()
        _isSlowSpeech.value = progressManager.isSlowSpeech()
        _isSoundEnabled.value = progressManager.isSoundEnabled()
        tts.speak("All progress has been reset for a fresh start!")
    }

    override fun onCleared() {
        super.onCleared()
        tts.shutdown()
    }
}
