/*
 * Tatarcham v0.2, tatar language learning app for Android.
 * Copyright (C) 2023-2025 Ayzat Rizatdinov <ddov4k1n at gmail dot com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 *
 */

package com.dov4k1n.tatarapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dov4k1n.tatarapp.data.SCORE_INCREASE
import com.dov4k1n.tatarapp.ui.state.ConjugationUiState
import com.dov4k1n.tatarapp.util.personalPronouns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

open class ConjugationViewModel(
    val wordSet: Set<String>,
    val findCorrectAnswer: (root: String, prefix: String) -> String
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConjugationUiState())
    val uiState: StateFlow<ConjugationUiState> = _uiState.asStateFlow()

    private lateinit var currentWord: String

    var userAnswer by mutableStateOf("")
        private set
    fun updateUserAnswer(answer: String) {
        userAnswer = answer
    }
    var previousUserAnswer = ""

    private fun pickRandomWord(): String {
        currentWord = wordSet.random()
        return currentWord
    }

    private var typedWrong by mutableStateOf(false)
    
    init {
        _uiState.value = ConjugationUiState(
            totalWords = wordSet.size,
            currentWord = pickRandomWord(),
            currentPrefix = personalPronouns.random(),
        )
    }

    private fun updateSessionState(updatedScore: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                totalWords = wordSet.size,
                isTypedAnsWrong = false,
                currentWord = pickRandomWord(),
                currentPrefix = personalPronouns.random(),
                currentWordCount = currentState.currentWordCount.inc(),
                score = updatedScore,
                wrongAnswers = currentState.wrongAnswers
            )
        }
    }

    internal fun checkUserAnswer() {
        val currentRoot = currentWord.replace(Regex("[0-9.]|(?<=\\.)\\s"), "")
        val correctAnswer = findCorrectAnswer(currentRoot, _uiState.value.currentPrefix)
        val increment = if (!typedWrong) SCORE_INCREASE else 0

        if (userAnswer.equals(correctAnswer, true)) {
            val updatedScore = _uiState.value.score.plus(increment)
            updateSessionState(updatedScore)
            updateUserAnswer("")
            typedWrong = false
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isTypedAnsWrong = true,
                    wrongAnswers = currentState.wrongAnswers.inc()
                )
            }
            previousUserAnswer = userAnswer
            updateUserAnswer(correctAnswer)
            typedWrong = true
        }
    }
}
