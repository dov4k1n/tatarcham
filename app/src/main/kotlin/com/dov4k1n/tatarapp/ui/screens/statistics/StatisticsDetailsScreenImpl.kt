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

package com.dov4k1n.tatarapp.ui.screens.statistics

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.local.StatsManager
import com.dov4k1n.tatarapp.data.local.addressToAnsCorrect
import com.dov4k1n.tatarapp.data.local.addressToAnsWrong
import com.dov4k1n.tatarapp.data.sectionAdjective
import com.dov4k1n.tatarapp.data.sectionNoun
import com.dov4k1n.tatarapp.data.sectionVerb

enum class StatisticsDetailsScreen {
    PhoneticsStats,
    LexiconStats,
    MorphologyStats,
    SyntaxStats,
    CultureStats,
}

@Composable
fun PhoneticsStatsScreen() {
    StatisticsDetailsScreen(
        title = R.string.bottom_bar_phonetics,
        color = colorScheme.onError
    )
}

@Composable
fun LexiconStatsScreen() {
    StatisticsDetailsScreen(
        title = R.string.bottom_bar_lexicon,
        color = colorScheme.inversePrimary
    )
}

@Composable
fun MorphologyStatsScreen() {

    val context = LocalContext.current
    val statsManager = StatsManager(context)

    StatisticsDetailsScreen(
        title = R.string.bottom_bar_morphology,
        color = colorScheme.onSecondary
    ) {
        LazyColumn {
            items(
                items = sectionVerb + sectionNoun + sectionAdjective,
                key = { it.nameAddress }
            ) {
                val correct = statsManager
                    .getAnsCorrect(addressToAnsCorrect(it.nameAddress))

                val wrong = statsManager
                    .getAnsCorrect(addressToAnsWrong(it.nameAddress))

                val total = correct + wrong

                StatisticsRow(
                    stringResource(it.nameAddress),
                    total.toString()
                )
                StatisticsRow(
                    stringResource(R.string.correct),
                    correct.toString(),
                    modifier = Modifier
                        .Companion.padding(start = 8.dp)
                )
                StatisticsRow(
                    stringResource(R.string.wrong),
                    wrong.toString(),
                    modifier = Modifier
                        .Companion.padding(start = 8.dp, bottom = 16.dp),
                    rightColor = colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun SyntaxStatsScreen() {
    StatisticsDetailsScreen(
        title = R.string.bottom_bar_syntax,
        color = colorScheme.errorContainer
    )
}

@Composable
fun CultureStatsScreen() {
    StatisticsDetailsScreen(
        title = R.string.bottom_bar_culture,
        color = colorScheme.onErrorContainer
    )
}
