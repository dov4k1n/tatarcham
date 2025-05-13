package com.dov4k1n.tatarapp.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.local.StatsManager
import com.dov4k1n.tatarapp.data.local.addressToAnsCorrect
import com.dov4k1n.tatarapp.data.local.addressToAnsWrong
import com.dov4k1n.tatarapp.data.local.addressToCatAnsCorrect
import com.dov4k1n.tatarapp.data.local.addressToCatAnsWrong
import com.dov4k1n.tatarapp.data.local.addressToCatTicks
import com.dov4k1n.tatarapp.data.local.addressToTicks
import com.dov4k1n.tatarapp.ui.TopAppBar
import com.dov4k1n.tatarapp.ui.components.ConjugationCard
import com.dov4k1n.tatarapp.ui.components.ProgressBarTimeAndStatistics
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme
import com.dov4k1n.tatarapp.ui.viewmodel.ConjugationViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.StopwatchViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.morphology.PresentViewModel

@Preview
@Composable
fun PreviewConjugationScreen() {
    TatarAppTheme(useDarkTheme = true) {
        TopAppBar(
            navController = NavHostController(LocalContext.current)
        )
        ConjugationScreen(
            levelNameResId = R.string.verb_present,
            taskMessage = "Put the verb into the present tense",
            theory = R.string.verb_present_theory,
            viewModel = PresentViewModel(),
            modifier = Modifier.background(color = colorScheme.background)
        )
    }
}

@Composable
fun ConjugationScreen(
    taskMessage: String,
    modifier: Modifier = Modifier,
    levelNameResId: Int,
    theory: Int = R.string.empty_string,
    newTheory: @Composable () -> Unit = {},
    viewModel: ConjugationViewModel = viewModel(),
    stopwatch: StopwatchViewModel = viewModel(),
    skipPrefix: Boolean = false
) {
    val sessionUiState by viewModel.uiState.collectAsState()

    val currentWordCount = sessionUiState.currentWordCount
    val score = sessionUiState.score
    val wrongAnswers = sessionUiState.wrongAnswers

    val percentage =
        if (currentWordCount != 1)
            (score.toDouble() / (currentWordCount.toDouble() - 1) * 100).toInt()
        else 0

    val progress: Float by animateFloatAsState(
        targetValue =
            if (score + wrongAnswers != 0)
                score.toFloat() / (score + wrongAnswers)
            else
                0.0F,
        label = ""
    )

    val ticks by stopwatch.ticks.collectAsState()
    val context = LocalContext.current
    val statsManager = StatsManager(context)

    DisposableEffect(Unit) {
        val lifecycleOwner = ProcessLifecycleOwner.get()
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> stopwatch.toggleTimer(true)
                Lifecycle.Event.ON_STOP -> stopwatch.toggleTimer(false)
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)

            statsManager.updateDaysPracticedCount()

            val keyTicks = addressToTicks(levelNameResId)
            val keyTicksCategory = addressToCatTicks(levelNameResId)
            statsManager.saveTicks(keyTicks, ticks)
            statsManager.saveTicksToCat(keyTicksCategory, ticks)

            val keyAnsCorrect = addressToAnsCorrect(levelNameResId)
            val keyAnsCorrectCategory = addressToCatAnsCorrect(levelNameResId)
            val correct = sessionUiState.score
            statsManager.saveAnsCorrect(keyAnsCorrect, correct)
            statsManager.saveAnsCorrectToCat(keyAnsCorrectCategory, correct)

            val keyAnsWrong = addressToAnsWrong(levelNameResId)
            val keyAnsWrongCategory = addressToCatAnsWrong(levelNameResId)
            val wrong = sessionUiState.wrongAnswers
            statsManager.saveAnsWrong(keyAnsWrong, wrong)
            statsManager.saveAnsWrongToCat(keyAnsWrongCategory, wrong)
        }
    }

    val sessionStatsShareMessage = stringResource(
        R.string.session_stats_share_message,
        score,
        percentage
    )


    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .imePadding()
    ) {

        ProgressBarTimeAndStatistics(
            progress = progress,
            ticks = ticks,
            correctAnswers = score,
            wrongAnswers = wrongAnswers,
            sessionStatsShareMessage = sessionStatsShareMessage,
            context = context,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ConjugationCard(
            levelName = stringResource(levelNameResId),
            taskMessage = taskMessage,
            theory = theory,
            newTheory = newTheory,
            currentVerb = sessionUiState.currentWord,
            skipPrefix = skipPrefix,
            currentPrefix = sessionUiState.currentPrefix,
            userAnswer = viewModel.userAnswer,
            previousUserAnswer = viewModel.previousUserAnswer,
            onUserTypeChanged = { viewModel.updateUserAnswer(it) },
            onKeyboardDone = { viewModel.checkUserAnswer() },
            isTypeWrong = sessionUiState.isTypedAnsWrong,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
