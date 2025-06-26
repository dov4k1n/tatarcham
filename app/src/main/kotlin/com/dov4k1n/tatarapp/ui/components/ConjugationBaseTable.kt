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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewConjugationBase() {
    TatarAppTheme(useDarkTheme = true) {
        ConjugationBase(
            conjugationBaseAffixOne = R.string.verb_present_base_affix_one,
            conjugationBaseAffixTwo = R.string.verb_present_base_affix_two,
            conjugationBaseCaseOne = R.string.verb_present_base_case_one,
            conjugationBaseCaseTwo = R.string.verb_present_base_case_two
        )
    }
}

@Composable
fun ConjugationBase(
    modifier: Modifier = Modifier,
    conjugationBaseAffixOne: Int = R.string.empty_string,
    conjugationBaseAffixTwo: Int = R.string.empty_string,
    conjugationBaseCaseOne: Int = R.string.empty_string,
    conjugationBaseCaseTwo: Int = R.string.empty_string,
) {
    val a1 = stringResource(conjugationBaseAffixOne)
    val a2 = stringResource(conjugationBaseAffixTwo)
    val c1 = stringResource(conjugationBaseCaseOne)
    val c2 = stringResource(conjugationBaseCaseTwo)

    Column(
        modifier = modifier,
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = colorScheme.secondary,
                    )
                ) {
                    append(a1)
                }
                withStyle(
                    SpanStyle(
                        color = colorScheme.primary,
                    )
                ) {
                    append(c1)
                }
            },
            style = typography.titleSmall,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = colorScheme.secondary,
                    )
                ) {
                    append(a2)
                }
                withStyle(
                    SpanStyle(
                        color = colorScheme.primary,
                    )
                ) {
                    append(c2)
                }
            },
            style = typography.titleSmall,
        )
    }
}