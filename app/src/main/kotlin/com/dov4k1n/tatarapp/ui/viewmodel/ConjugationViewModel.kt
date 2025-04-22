package com.dov4k1n.tatarapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dov4k1n.tatarapp.data.SCORE_INCREASE
import com.dov4k1n.tatarapp.ui.state.SessionUiState
import com.dov4k1n.tatarapp.util.personalPronouns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

open class ConjugationViewModel(
    val wordSet: Set<String>,
    val findCorrectAnswer: (root: String, prefix: String) -> String
) : ViewModel() {

    private val _uiState = MutableStateFlow(SessionUiState())
    val uiState: StateFlow<SessionUiState> = _uiState.asStateFlow()

    private lateinit var currentWord: String

    var userAnswer by mutableStateOf("")
        private set
    fun updateUserAnswer(answer: String) {
        userAnswer = answer
    }
    var previousUserAnswer = ""

    private var typedWrong by mutableStateOf(false)

    internal fun resetGame() {
        _uiState.value = SessionUiState(
            totalWords = wordSet.size,
            currentWord = wordSet.random(),
            currentPrefix = personalPronouns.random(),
        )
    }

    init {
        resetGame()
    }

    private fun updateSessionState(updatedScore: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                totalWords = wordSet.size,
                isTypedAnsWrong = false,
                currentWord = wordSet.random(),
                currentPrefix = personalPronouns.random(),
                currentWordCount = currentState.currentWordCount.inc(),
                score = updatedScore,
                wrongAnswers = currentState.wrongAnswers
            )
        }
    }

    internal fun skipWord() {
        updateSessionState(_uiState.value.score)
        updateUserAnswer("")
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
