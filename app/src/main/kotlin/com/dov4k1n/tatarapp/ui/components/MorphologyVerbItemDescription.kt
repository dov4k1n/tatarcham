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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.SectionVerbData
import com.dov4k1n.tatarapp.data.sectionVerb
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewMorphologyItemDescription() {
    TatarAppTheme(useDarkTheme = true) {
        VerbItemDescription(
            sectionData = sectionVerb[2],
            modifier = Modifier
                .background(color = colorScheme.onPrimaryContainer)
        )
    }
}

@Composable
fun VerbItemDescription(
    sectionData: SectionVerbData,
    modifier: Modifier = Modifier
) {
    MorphologyItemDescription(
        modifier = modifier
    ) {
        DescriptionRow(
            leftText = stringResource(id = R.string.action_time),
            rightText = stringResource(id = sectionData.description.actionTime)
        )
        DescriptionRow(
            leftText = stringResource(id = R.string.question),
            rightText = stringResource(id = sectionData.description.question),
            modifier = Modifier
                .padding(top = 12.dp)
        )
        ConjugationBaseDescriptionRow(
            leftText = stringResource(id = R.string.conjugation_base),
            affixOne = stringResource(id = sectionData.description.conjugationBaseAffixOne),
            caseOne = stringResource(id = sectionData.description.conjugationBaseCaseOne),
            affixTwo = stringResource(id = sectionData.description.conjugationBaseAffixTwo),
            caseTwo = stringResource(id = sectionData.description.conjugationBaseCaseTwo),
            modifier = Modifier
                .padding(top = 12.dp)
        )
        DescriptionRow(
            leftText = stringResource(id = R.string.personal_affixes),
            rightText = stringResource(id = sectionData.description.personalAffixes),
            rightTextColor = colorScheme.secondary,
            modifier = Modifier
                .padding(top = 12.dp)
        )
        NegationFormDescriptionRow(
            affix = stringResource(id = sectionData.description.negativeForm),
            modifier = Modifier
                .padding(top = 12.dp, bottom = 24.dp)
        )
    }
}

@Composable
fun MorphologyItemDescription(
    modifier: Modifier = Modifier,
    description: @Composable () -> Unit = {}
) {
    var expandedMore by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = stringResource(R.string.about),
            style = typography.bodyMedium,
            color = colorScheme.tertiary,
            modifier = Modifier
                .fillMaxWidth()
        )
        description()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { expandedMore = !expandedMore }
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.more),
                style = typography.bodyMedium,
                color = colorScheme.tertiary
            )
            Icon(
                imageVector =
                    if (expandedMore)
                        Icons.Outlined.ExpandLess
                    else
                        Icons.Outlined.ExpandMore,
                contentDescription = null,
                tint = colorScheme.tertiary,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(16.dp)
            )
        }

        AnimatedVisibility(expandedMore) {
            Text(
                text = stringResource(id = R.string.under_development),
                style = typography.bodyMedium,
                color = colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun DescriptionRow(
    leftText: String,
    rightText: String,
    modifier: Modifier = Modifier,
    rightTextColor: Color = colorScheme.primary
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = leftText,
            style = typography.bodyMedium,
            color = colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = rightText,
            style = typography.bodyMedium,
            color = rightTextColor,
            textAlign = TextAlign.Right,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ConjugationBaseDescriptionRow(
    leftText: String,
    affixOne: String,
    caseOne: String,
    affixTwo: String,
    caseTwo: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = leftText,
            style = typography.bodyMedium,
            color = colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = affixOne,
                style = typography.bodyMedium,
                color = colorScheme.secondary,
                textAlign = TextAlign.End
            )
            Text(
                text = caseOne,
                style = typography.bodyMedium,
                color = colorScheme.primary,
                textAlign = TextAlign.End
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = affixTwo,
                style = typography.bodyMedium,
                color = colorScheme.secondary,
                textAlign = TextAlign.End
            )
            Text(
                text = caseTwo,
                style = typography.bodyMedium,
                color = colorScheme.primary,
                textAlign = TextAlign.End
            )
        }

    }
}

@Composable
fun NegationFormDescriptionRow(
    affix: String,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.negative_form),
            style = typography.bodyMedium,
            color = colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = affix,
                style = typography.bodyMedium,
                color = colorScheme.secondary,
                textAlign = TextAlign.Right
            )
            Text(
                text = stringResource(id = R.string.to_the_root),
                style = typography.bodyMedium,
                color = colorScheme.primary,
                textAlign = TextAlign.Right
            )
        }

    }
}