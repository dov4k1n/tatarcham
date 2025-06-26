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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Fingerprint
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview(showBackground = false)
@Composable
fun PreviewTheoryBlock() {
    TatarAppTheme(useDarkTheme = true) {
        TheoryBlock(
            icon = Icons.Outlined.Fingerprint,
            heading = "Lexiconnnnnnnnnnnnnnnnnnnnnnnnnnnnn",
            headingColor = colorScheme.onError,
            subheading = "General information"
        )
    }
}
@Preview(showBackground = false)
@Composable
fun PreviewIntervalTrainingBlock() {
    TatarAppTheme(useDarkTheme = true) {
        IntervalTrainingBlock(
            icon = Icons.Outlined.Memory,
            heading = "Traininggggggggggggggggggggggggggg",
            headingColor = colorScheme.onError,
            subheading = "Spaced repetitions"
        )
    }
}
@Preview(showBackground = false)
@Composable
fun PreviewHeroBlock() {
    TatarAppTheme(useDarkTheme = true) {
        HeroBlock(
            icon = Icons.Outlined.Explore,
            heading = "Dictionaryyyyyyyyyyyyyyyyyyyyyyyyy",
            headingColor = colorScheme.onError,
            subheading = "Explore new words"
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    tint = colorScheme.inversePrimary
                )
            }
        }
    }
}


@Composable
fun TheoryBlock(
    icon: ImageVector,
    heading: String,
    subheading: String = "",
    headingColor: Color,
    modifier: Modifier = Modifier,
    action: () -> Unit = {},
) {
    HeroBlock(
        icon = icon,
        heading = heading,
        headingColor = headingColor,
        modifier = modifier.clickable { action() },
        subheading = subheading,
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.MenuBook,
                contentDescription = null,
                tint = colorScheme.primary
            )
        }
    }
}

@Composable
fun IntervalTrainingBlock(
    icon: ImageVector,
    heading: String,
    headingColor: Color,
    modifier: Modifier = Modifier,
    subheading: String = "",
) {
    HeroBlock(
        icon = icon,
        heading = heading,
        headingColor = headingColor,
        modifier = modifier,
        subheading = subheading,
    ) {
        Row {
            Text(
                text = "20 012",
                style = typography.bodySmall,
                color = colorScheme.onError,
                modifier = Modifier
                    .padding(end = 16.dp)
            )
            Text(
                text = "500",
                style = typography.bodySmall,
                color = colorScheme.onBackground,
                modifier = Modifier
                    .padding(end = 16.dp)
            )
            Text(
                text = "1312",
                style = typography.bodySmall,
                color = colorScheme.tertiary
            )
        }
    }
}

@Composable
fun HeroBlock(
    icon: ImageVector,
    heading: String,
    headingColor: Color,
    modifier: Modifier = Modifier,
    subheading: String = "",
    actionableComposable: @Composable () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = headingColor,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(
                    text = heading,
                    style = typography.titleLarge,
                    color = headingColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = subheading,
                style = typography.bodySmall,
                color = colorScheme.onBackground,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        actionableComposable()
    }
}