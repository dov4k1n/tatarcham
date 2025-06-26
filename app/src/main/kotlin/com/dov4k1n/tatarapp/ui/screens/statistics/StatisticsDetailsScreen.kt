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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.local.StatsManager
import com.dov4k1n.tatarapp.data.local.ThemeMode
import com.dov4k1n.tatarapp.data.local.tabAddressToCatAnsCorrect
import com.dov4k1n.tatarapp.data.local.tabAddressToCatAnsWrong
import com.dov4k1n.tatarapp.data.local.tabAddressToCatTicks
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewStatisticsDetailsScreen() {
    TatarAppTheme(themeMode = ThemeMode.Dark) {
        StatisticsDetailsScreen(
            title = R.string.bottom_bar_morphology,
            color = colorScheme.onSecondary
        )
    }
}

@Composable
fun StatisticsDetailsScreen(
    title: Int,
    color: Color = colorScheme.primary,
    composable: @Composable () -> Unit = { },
) {
    val context = LocalContext.current
    val statsManager = StatsManager(context)

    val keyTicks = tabAddressToCatTicks(title)
    val keyAnsCorrect = tabAddressToCatAnsCorrect(title)
    val keyAnsWrong = tabAddressToCatAnsWrong(title)

    val ticks = "%.1f".format(
        statsManager.getTicksCategory(keyTicks) / 3600.0
    ) + stringResource(R.string.hours)
    val correct = statsManager.getAnsCorrectCategory(keyAnsCorrect)
    val wrong = statsManager.getAnsWrongCategory(keyAnsWrong)
    val total = correct + wrong

    Column {
        StatisticsTitleH1(
            text = stringResource(title),
            color = color,
            modifier = Modifier.padding(bottom = 24.dp),
        )
        StatisticsTitleH2(
            stringResource(R.string.time),
            Modifier.Companion.padding(bottom = 8.dp)
        )
        StatisticsRow(
            stringResource(R.string.practice_time),
            ticks,
            modifier = Modifier.Companion.padding(bottom = 12.dp),
        )

        StatisticsTitleH2(
            stringResource(R.string.answers),
            Modifier.Companion.padding(bottom = 8.dp)
        )
        StatisticsRow(
            stringResource(R.string.total),
            total.toString()
        )
        StatisticsRow(
            stringResource(R.string.correct),
            correct.toString()
        )
        StatisticsRow(
            stringResource(R.string.wrong),
            wrong.toString(),
            modifier = Modifier.Companion.padding(bottom = 24.dp),
            rightColor = colorScheme.onBackground,
        )

        StatisticsTitleH2(
            stringResource(R.string.exercises),
            Modifier.Companion.padding(bottom = 8.dp)
        )
        composable()
    }
}
