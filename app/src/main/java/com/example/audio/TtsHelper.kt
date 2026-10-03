package com.example.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import java.util.Locale

class TtsHelper(context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private var isReady = false
    private var slowMode = false
    private var soundEnabled = true

    init {
        tts = TextToSpeech(context.applicationContext, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.w("TtsHelper", "US English language not supported or missing data")
                tts?.setLanguage(Locale.getDefault())
            }
            // Friendly slightly higher pitch for kids
            tts?.setPitch(1.15f)
            updateRate()
            isReady = true

            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {}
                override fun onDone(utteranceId: String?) {}
                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) {}
            })
        } else {
            Log.e("TtsHelper", "TTS Initialization failed")
        }
    }

    fun setSlowMode(enabled: Boolean) {
        slowMode = enabled
        updateRate()
    }

    fun setSoundEnabled(enabled: Boolean) {
        soundEnabled = enabled
        if (!enabled) {
            stop()
        }
    }

    private fun updateRate() {
        if (slowMode) {
            tts?.setSpeechRate(0.72f) // Gentle slow rate for toddlers
        } else {
            tts?.setSpeechRate(0.92f) // Clear child-friendly natural pacing
        }
    }

    fun speak(text: String, flush: Boolean = true) {
        if (!soundEnabled || !isReady) return
        val queueMode = if (flush) TextToSpeech.QUEUE_FLUSH else TextToSpeech.QUEUE_ADD
        tts?.speak(text, queueMode, null, "utterance_${System.currentTimeMillis()}")
    }

    fun speakAnimalSound(name: String, soundEffect: String) {
        val message = "The $name says $soundEffect"
        speak(message)
    }

    fun speakPhonics(name: String) {
        if (!soundEnabled || !isReady) return
        val lettersSpaced = name.uppercase().toCharArray().joinToString(separator = ". ")
        val phonicsText = "$lettersSpaced... spells $name!"
        speak(phonicsText)
    }

    fun speakCelebration(message: String = "Awesome job! You got it right! ⭐") {
        speak(message)
    }

    fun speakEncouragement() {
        val encouragements = listOf(
            "Almost! Let's try again!",
            "Good try! Give it another go!",
            "You can do it! Try one more time!"
        )
        speak(encouragements.random())
    }

    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
        isReady = false
    }
}
