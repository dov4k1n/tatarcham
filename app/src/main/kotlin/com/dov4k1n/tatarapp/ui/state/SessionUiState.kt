package com.dov4k1n.tatarapp.ui.state

data class SessionUiState(
    val currentWord: String = "",
    val currentPrefix: String = "",
    val currentWordCount: Int = 1,
    val totalWords: Int = 1,
    val score: Int = 0,
    val wrongAnswers: Int = 0,
    val isTypedAnsWrong: Boolean = false,
    val isGameOver: Boolean = false
)