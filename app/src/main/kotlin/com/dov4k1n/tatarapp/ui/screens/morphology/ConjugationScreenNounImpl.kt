package com.dov4k1n.tatarapp.ui.screens.morphology

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.screens.ConjugationScreen
import com.dov4k1n.tatarapp.ui.viewmodel.morphology.PluralViewModel

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