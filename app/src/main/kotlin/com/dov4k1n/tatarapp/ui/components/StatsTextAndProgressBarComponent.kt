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

package com.dov4k1n.tatarapp.ui.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.theme.Shapes
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewProgressBarTimeAndStatistics() {
    TatarAppTheme(useDarkTheme = true) {
        ProgressBarTimeAndStatistics(
            progress = 0.75f,
            ticks = 100,
            correctAnswers = 15,
            wrongAnswers = 5,
            sessionStatsShareMessage = "",
            context = LocalContext.current
        )
    }
}

@Composable
fun ProgressBarTimeAndStatistics(
    progress: Float,
    ticks: Int,
    correctAnswers: Int,
    wrongAnswers: Int,
    sessionStatsShareMessage: String,
    context: Context,
    modifier: Modifier = Modifier
) {
    val secondsInt: Int = ticks % 60
    val totalMinutesInt: Int = ticks / 60
    val minutesInt: Int = totalMinutesInt % 60
    val hoursInt: Int = totalMinutesInt / 60

    val seconds: String = when (secondsInt) {
        in 0..9 -> "0$secondsInt"
        else -> "$secondsInt"
    }

    val minutes: String = when (minutesInt) {
        in 0..9 -> "0$minutesInt"
        else -> "$minutesInt"
    }

    val hours: String = when (hoursInt) {
        in 0..9 -> "0$hoursInt"
        else -> "$hoursInt"
    }

    val time: String = if (hoursInt > 0) "$hours:$minutes:$seconds" else "$minutes:$seconds"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clickable { shareSessionStats(context, sessionStatsShareMessage) }
    ) {
        LinearProgressIndicator(
            progress = { progress },
            color = colorScheme.tertiary,
            trackColor = colorScheme.primaryContainer,
//            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(
                text = time,
                style = typography.titleSmall,
                color = colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Row {
                Card(
                    shape = Shapes.small,
                    border = BorderStroke(
                        width = 1.dp,
                        color = colorScheme.tertiary
                    ),
                    colors = CardColors(
                        contentColor = colorScheme.tertiary,
                        containerColor = Color.Transparent,
                        disabledContentColor = colorScheme.tertiary,
                        disabledContainerColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .padding(end = 8.dp)
                ) {
                    Text(
                        text = correctAnswers.toString(),
                        style = typography.titleSmall,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                Card(
                    shape = Shapes.small,
                    border = BorderStroke(
                        width = 1.dp,
                        color = colorScheme.primaryContainer
                    ),
                    colors = CardColors(
                        contentColor = colorScheme.error,
                        containerColor = Color.Transparent,
                        disabledContentColor = colorScheme.error,
                        disabledContainerColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .padding(end = 8.dp)
                ) {
                    Text(
                        text = wrongAnswers.toString(),
                        style = typography.titleSmall,
                        color = colorScheme.error,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                Text(
                    text = (progress * 100).toInt().toString() + "%",
                    style = typography.titleSmall,
                    color = colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 8.dp)
                )
            }
        }
    }
}

private fun shareSessionStats(
    context: Context,
    sessionStatsShareMessage: String
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, sessionStatsShareMessage)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.session_stats_share_title)
        )
    )
}