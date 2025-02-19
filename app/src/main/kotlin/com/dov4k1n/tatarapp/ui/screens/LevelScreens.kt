package com.dov4k1n.tatarapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.components.DefinitePastTheory
import com.dov4k1n.tatarapp.ui.components.IndefinitePastTheory
import com.dov4k1n.tatarapp.ui.components.PastContinuousTheory
import com.dov4k1n.tatarapp.ui.components.PresentTheory
import com.dov4k1n.tatarapp.ui.viewmodel.DefiniteFutureViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.DefinitePastViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.FutureInThePastViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.GerundViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.IndefiniteFutureViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.IndefinitePastViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.InfinitiveViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.PastContinuousViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.PastPerfectViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.PluralViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.PresentViewModel

@Composable
fun VerbPresentScreen() {
    ConjugationScreen(
        levelName = stringResource(R.string.verb_present),
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = PresentViewModel(),
        theory = R.string.empty_string, // R.string.verb_present_theory
        newTheory = { PresentTheory() }
    )
}

@Composable
fun VerbDefinitePastScreen() {
    ConjugationScreen(
        levelName = stringResource(R.string.verb_definite_past),
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = DefinitePastViewModel(),
        theory = R.string.empty_string, // R.string.verb_definite_past_theory
        newTheory = { DefinitePastTheory() }
    )
}

@Composable
fun VerbIndefinitePastScreen() {
    ConjugationScreen(
        levelName = stringResource(R.string.verb_indefinite_past),
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = IndefinitePastViewModel(),
        theory = R.string.empty_string, // R.string.verb_indefinite_past_theory
        newTheory = { IndefinitePastTheory() }
    )
}

@Composable
fun VerbPastContinuousScreen() {
    ConjugationScreen(
        levelName = stringResource(R.string.verb_past_continuous),
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = PastContinuousViewModel(),
        theory = R.string.empty_string, // R.string.verb_past_continuous_theory
        newTheory = { PastContinuousTheory() }
    )
}

@Composable
fun VerbPastPerfectScreen() {
    ConjugationScreen(
        levelName = stringResource(R.string.verb_past_perfect),
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = PastPerfectViewModel(),
        theory = R.string.verb_past_perfect_theory
    )
}

@Composable
fun VerbDefiniteFutureScreen() {
    ConjugationScreen(
        levelName = stringResource(R.string.verb_definite_future),
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = DefiniteFutureViewModel(),
        theory = R.string.verb_definite_future_theory
    )
}

@Composable
fun VerbIndefiniteFutureScreen() {
    ConjugationScreen(
        levelName = stringResource(R.string.verb_indefinite_future),
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = IndefiniteFutureViewModel(),
        theory = R.string.verb_indefinite_future_theory
    )
}

@Composable
fun VerbInfinitiveScreen() {
    ConjugationScreen(
        levelName = stringResource(R.string.verb_infinitive),
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = InfinitiveViewModel(),
        skipPrefix = true,
        theory = R.string.verb_infinitive_theory
    )
}

@Composable
fun VerbGerundScreen() {
    ConjugationScreen(
        levelName = stringResource(R.string.verb_gerund),
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = GerundViewModel(),
        skipPrefix = true,
        theory = R.string.verb_gerund_theory
    )
}

@Composable
fun VerbFutureInThePastScreen() {
    ConjugationScreen(
        levelName = stringResource(R.string.verb_future_in_the_past),
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = FutureInThePastViewModel(),
        theory = R.string.verb_future_in_the_past_theory
    )
}

@Composable
fun NounPluralScreen() {
    ConjugationScreen(
        levelName = stringResource(R.string.noun_plural),
        taskMessage = stringResource(R.string.non_verb_task_message),
        viewModel = PluralViewModel(),
        theory = R.string.noun_plural_theory,
        skipPrefix = true
    )
}