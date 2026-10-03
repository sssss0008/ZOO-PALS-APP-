package com.example.data

import android.content.Context
import android.content.SharedPreferences

class UserProgressManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("zoopals_progress_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_STARS = "total_stars"
        private const val KEY_FAVORITES = "favorite_animal_ids"
        private const val KEY_STICKERS = "unlocked_sticker_ids"
        private const val KEY_SLOW_SPEECH = "is_slow_speech"
        private const val KEY_SOUND_ENABLED = "is_sound_enabled"
        private const val KEY_QUIZ_STREAK = "quiz_streak"
    }

    fun getTotalStars(): Int = prefs.getInt(KEY_STARS, 5) // Give 5 free starter stars to reward right away!

    fun addStars(amount: Int) {
        val current = getTotalStars()
        prefs.edit().putInt(KEY_STARS, current + amount).apply()
    }

    fun spendStars(amount: Int): Boolean {
        val current = getTotalStars()
        if (current >= amount) {
            prefs.edit().putInt(KEY_STARS, current - amount).apply()
            return true
        }
        return false
    }

    fun getFavoriteIds(): Set<String> {
        return prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
    }

    fun toggleFavorite(animalId: String): Boolean {
        val current = getFavoriteIds().toMutableSet()
        val isNowFavorite = if (current.contains(animalId)) {
            current.remove(animalId)
            false
        } else {
            current.add(animalId)
            true
        }
        prefs.edit().putStringSet(KEY_FAVORITES, current).apply()
        return isNowFavorite
    }

    fun getUnlockedStickers(): Set<String> {
        // Unlock starter pack: lion, cow, dolphin
        val defaultSet = setOf("lion", "cow", "dolphin")
        val saved = prefs.getStringSet(KEY_STICKERS, null)
        return saved ?: defaultSet
    }

    fun unlockSticker(animalId: String) {
        val current = getUnlockedStickers().toMutableSet()
        current.add(animalId)
        prefs.edit().putStringSet(KEY_STICKERS, current).apply()
    }

    fun isSlowSpeech(): Boolean = prefs.getBoolean(KEY_SLOW_SPEECH, false)

    fun setSlowSpeech(slow: Boolean) {
        prefs.edit().putBoolean(KEY_SLOW_SPEECH, slow).apply()
    }

    fun isSoundEnabled(): Boolean = prefs.getBoolean(KEY_SOUND_ENABLED, true)

    fun setSoundEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_SOUND_ENABLED, enabled).apply()
    }

    fun getQuizStreak(): Int = prefs.getInt(KEY_QUIZ_STREAK, 0)

    fun recordQuizAnswer(correct: Boolean): Int {
        val current = if (correct) getQuizStreak() + 1 else 0
        prefs.edit().putInt(KEY_QUIZ_STREAK, current).apply()
        return current
    }

    fun resetAllProgress() {
        prefs.edit().clear().apply()
    }
}
