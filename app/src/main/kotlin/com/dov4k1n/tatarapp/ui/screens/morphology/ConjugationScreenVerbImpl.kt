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

package com.dov4k1n.tatarapp.ui.screens.morphology

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.components.DefinitePastTheory
import com.dov4k1n.tatarapp.ui.components.IndefinitePastTheory
import com.dov4k1n.tatarapp.ui.components.PastContinuousTheory
import com.dov4k1n.tatarapp.ui.components.PresentTheory
import com.dov4k1n.tatarapp.ui.screens.ConjugationScreen
import com.dov4k1n.tatarapp.ui.viewmodel.morphology.DefiniteFutureViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.morphology.DefinitePastViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.morphology.FutureInThePastViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.morphology.GerundViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.morphology.IndefiniteFutureViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.morphology.IndefinitePastViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.morphology.InfinitiveViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.morphology.PastContinuousViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.morphology.PastPerfectViewModel
import com.dov4k1n.tatarapp.ui.viewmodel.morphology.PresentViewModel

@Composable
fun VerbPresentScreen() {
    ConjugationScreen(
        levelNameResId = R.string.verb_present,
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = PresentViewModel(),
        theory = R.string.empty_string, // R.string.verb_present_theory
        newTheory = { PresentTheory() }
    )
}

@Composable
fun VerbDefinitePastScreen() {
    ConjugationScreen(
        levelNameResId = R.string.verb_definite_past,
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = DefinitePastViewModel(),
        theory = R.string.empty_string, // R.string.verb_definite_past_theory
        newTheory = { DefinitePastTheory() }
    )
}

@Composable
fun VerbIndefinitePastScreen() {
    ConjugationScreen(
        levelNameResId = R.string.verb_indefinite_past,
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = IndefinitePastViewModel(),
        theory = R.string.empty_string, // R.string.verb_indefinite_past_theory
        newTheory = { IndefinitePastTheory() }
    )
}

@Composable
fun VerbPastContinuousScreen() {
    ConjugationScreen(
        levelNameResId = R.string.verb_past_continuous,
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = PastContinuousViewModel(),
        theory = R.string.empty_string, // R.string.verb_past_continuous_theory
        newTheory = { PastContinuousTheory() }
    )
}

@Composable
fun VerbPastPerfectScreen() {
    ConjugationScreen(
        levelNameResId = R.string.verb_past_perfect,
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = PastPerfectViewModel(),
        theory = R.string.verb_past_perfect_theory
    )
}

@Composable
fun VerbDefiniteFutureScreen() {
    ConjugationScreen(
        levelNameResId = R.string.verb_definite_future,
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = DefiniteFutureViewModel(),
        theory = R.string.verb_definite_future_theory
    )
}

@Composable
fun VerbIndefiniteFutureScreen() {
    ConjugationScreen(
        levelNameResId = R.string.verb_indefinite_future,
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = IndefiniteFutureViewModel(),
        theory = R.string.verb_indefinite_future_theory
    )
}

@Composable
fun VerbInfinitiveScreen() {
    ConjugationScreen(
        levelNameResId = R.string.verb_infinitive,
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = InfinitiveViewModel(),
        skipPrefix = true,
        theory = R.string.verb_infinitive_theory
    )
}

@Composable
fun VerbGerundScreen() {
    ConjugationScreen(
        levelNameResId = R.string.verb_gerund,
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = GerundViewModel(),
        skipPrefix = true,
        theory = R.string.verb_gerund_theory
    )
}

@Composable
fun VerbFutureInThePastScreen() {
    ConjugationScreen(
        levelNameResId = R.string.verb_future_in_the_past,
        taskMessage = stringResource(R.string.verb_task_message),
        viewModel = FutureInThePastViewModel(),
        theory = R.string.verb_future_in_the_past_theory
    )
}
