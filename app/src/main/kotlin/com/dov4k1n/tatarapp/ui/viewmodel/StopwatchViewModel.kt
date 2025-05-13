package com.dov4k1n.tatarapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StopwatchViewModel() : ViewModel() {
    private val _ticks = MutableStateFlow(0)
    val ticks: StateFlow<Int> = _ticks.asStateFlow()

    private var timerJob: Job? = null

    fun toggleTimer(isForeground: Boolean) {
        if (isForeground) {
            startTimer()
        } else {
            stopTimer()
        }
    }

    private fun startTimer() {
        if (timerJob?.isActive == true) return

        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _ticks.value++
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    override fun onCleared() {
        stopTimer()
        super.onCleared()
    }
}