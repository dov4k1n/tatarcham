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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewPersonalAffixes() {
    TatarAppTheme(useDarkTheme = true) {
        PersonalAffixes(
            affixesColumn = R.string.verb_present_affixes
        )
    }
}

@Composable
fun PersonalAffixes(
    modifier: Modifier = Modifier,
    affixesColumn: Int = R.string.empty_string
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.person_tat),
                style = typography.bodyMedium,
                color = colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(R.string.pronouns_personal_tat),
                style = typography.titleSmall,
                color = colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.person_eng),
                style = typography.bodyMedium,
                color = colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(R.string.pronouns_personal),
                style = typography.titleSmall,
                color = colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.affix),
                style = typography.bodyMedium,
                color = colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(affixesColumn),
                style = typography.titleSmall,
                color = colorScheme.secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}