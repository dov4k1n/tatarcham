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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.TatarApplication
import com.dov4k1n.tatarapp.data.bottomAppBarTabsList
import com.dov4k1n.tatarapp.data.local.ThemeMode
import com.dov4k1n.tatarapp.ui.components.NonExpandableListItem
import com.dov4k1n.tatarapp.ui.theme.ListItemShape
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewStatisticsScreen() {
    TatarAppTheme(themeMode = ThemeMode.Dark) {
        StatisticsScreen()
    }
}

@Composable
fun StatisticsScreen(
    onNavigateToDetails: (Int) -> Unit = { }
) {
    val iconsColor = MaterialTheme.colorScheme.onBackground

    val context = LocalContext.current
    val statsManager =
        (context.applicationContext as TatarApplication).statsManager

    val firstLaunchDay = statsManager.getDayFirstLaunch()
    val daysPassedCount = statsManager.getDaysPassedCount().toString()
    val practiceTime = "%.1f".format(
        statsManager.getTicksTotal() / 3600.0
    ) + stringResource(R.string.hours)
    val dayLastPractice = statsManager.getDayLastPractice()
    val daysPracticedCount = statsManager.getDaysPracticedCount().toString()
    val ansCorrectTotal = statsManager.getAnsCorrectTotal().toString()
    val ansWrongTotal = statsManager.getAnsWrongTotal().toString()
    val ansTotal = (ansCorrectTotal.toInt() + ansWrongTotal.toInt()).toString()

    val deletedConfirmMsg = stringResource(R.string.all_deleted_confirmation)

    LazyColumn {
        item {
            StatisticsTitleH2(
                stringResource(R.string.time),
                Modifier.Companion.padding(bottom = 8.dp)
            )
        }
        item {
            StatisticsRow(
                stringResource(R.string.first_launch),
                firstLaunchDay
            )
        }
        item {
            StatisticsRow(
                stringResource(R.string.days_passed),
                daysPassedCount
            )
        }
        item {
            StatisticsRow(
                stringResource(R.string.practice_days),
                daysPracticedCount
            )
        }
        item {
            StatisticsRow(
                stringResource(R.string.practice_time),
                practiceTime
            )
        }
        item {
            StatisticsRow(
                stringResource(R.string.last_practiced_day),
                dayLastPractice,
                modifier = Modifier.Companion.padding(bottom = 12.dp),
            )
        }

        item {
            StatisticsTitleH2(
                stringResource(R.string.answers),
                Modifier.Companion.padding(bottom = 8.dp)
            )
        }
        item {
            StatisticsRow(
                stringResource(R.string.total),
                ansTotal
            )
        }
        item {
            StatisticsRow(
                stringResource(R.string.correct),
                ansCorrectTotal
            )
        }
        item {
            StatisticsRow(
                stringResource(R.string.wrong),
                ansWrongTotal,
                modifier = Modifier.Companion.padding(bottom = 24.dp),
                rightColor = MaterialTheme.colorScheme.onBackground,
            )
        }

        items(
            items = bottomAppBarTabsList,
            key = { it.title }
        ) {
            val shape = when (it.title) {
                R.string.bottom_bar_phonetics -> ListItemShape.small
                R.string.bottom_bar_culture -> ListItemShape.large
                else -> ListItemShape.medium
            }

            val padding = when (it.title) {
                R.string.bottom_bar_culture -> 24.dp
                else -> 2.dp
            }

            NonExpandableListItem(
                title = stringResource(id = it.title),
                icon = it.unselectedIcon,
                iconColor = iconsColor,
                onClick = { onNavigateToDetails(it.title) },
                shape = shape,
                modifier = Modifier.Companion
                    .padding(bottom = padding)
            )
        }

        item {
            NonExpandableListItem(
                title = stringResource(id = R.string.save_to_file),
                icon = Icons.Outlined.Save,
                iconColor = iconsColor,
                onClick = { statsManager.exportSharedPreferences(context) },
                shape = ListItemShape.small,
                modifier = Modifier.Companion
                    .padding(bottom = 2.dp)
            )
        }
        item {
            NonExpandableListItem(
                title = stringResource(id = R.string.load_from_file),
                icon = Icons.Outlined.Upload,
                iconColor = iconsColor,
                onClick = { /*TODO*/ },
                shape = ListItemShape.medium,
                modifier = Modifier.Companion
                    .padding(bottom = 2.dp)
            )
        }
        item {
            NonExpandableListItem(
                title = stringResource(id = R.string.clear_all_data),
                titleColor = MaterialTheme.colorScheme.error,
                icon = Icons.Outlined.Delete,
                iconColor = MaterialTheme.colorScheme.error,
                onClick = {
                    statsManager.clearAllData(context, deletedConfirmMsg)
                },
                shape = ListItemShape.large,
                modifier = Modifier.Companion
                    .padding(bottom = 8.dp)
            )
        }

    }
}


@Composable
fun StatisticsTitleH1(
    text: String,
    modifier: Modifier = Modifier.Companion,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = color,
        )
    }
}

@Composable
fun StatisticsTitleH2(
    text: String,
    modifier: Modifier = Modifier.Companion,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = color,
        modifier = modifier
            .padding(horizontal = 16.dp),
        maxLines = 1,
        overflow = TextOverflow.Companion.Ellipsis
    )
}

@Composable
fun StatisticsRow(
    left: String,
    right: String,
    modifier: Modifier = Modifier.Companion,
    rightColor: Color = MaterialTheme.colorScheme.primary
) {
    val rightColorActual =
        if (right == "0")
            MaterialTheme.colorScheme.onBackground
        else
            rightColor

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {
        Text(
            text = left,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            overflow = TextOverflow.Companion.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier.Companion
                .padding(end = 8.dp)
                .weight(1f)
        )
        Text(
            text = right,
            style = MaterialTheme.typography.bodyMedium,
            color = rightColorActual,
            maxLines = 2,
            overflow = TextOverflow.Companion.Ellipsis,
            textAlign = TextAlign.End,
            modifier = Modifier
                .weight(1f)
        )
    }
}
