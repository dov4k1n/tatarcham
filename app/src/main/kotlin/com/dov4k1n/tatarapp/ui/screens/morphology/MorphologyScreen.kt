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

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DoubleArrow
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.SensorOccupied
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.BottomAppBarItem
import com.dov4k1n.tatarapp.data.sectionAdjective
import com.dov4k1n.tatarapp.data.sectionNoun
import com.dov4k1n.tatarapp.data.sectionVerb
import com.dov4k1n.tatarapp.ui.components.ExpandableListItem
import com.dov4k1n.tatarapp.ui.components.TheoryBlock
import com.dov4k1n.tatarapp.ui.theme.ListItemShape
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewMorphologyScreen() {
    TatarAppTheme(useDarkTheme = true) {
        MorphologyScreen {

        }
    }
}

@Composable
fun MorphologyScreen(
    onPlayButtonClicked: (Int) -> Unit
) {
    LazyColumn {
        item {
            TheoryBlock(
                icon = BottomAppBarItem.Morphology.selectedIcon,
                heading = stringResource(R.string.bottom_bar_morphology),
                headingColor = MaterialTheme.colorScheme.onSecondary,
                subheading = stringResource(id = R.string.introduction_subheading),
                modifier = Modifier.Companion
                    .padding(bottom = 8.dp)
            )
        }


        item {
            TheoryBlock(
                icon = Icons.Outlined.DoubleArrow,
                heading = stringResource(id = R.string.verb),
                headingColor = MaterialTheme.colorScheme.onSecondary,
                subheading = stringResource(id = R.string.verb_subheading),
            )
        }
        items(
            items = sectionVerb,
            key = { it.nameAddress }
        ) {
            val shape = when (it.id) {
                1 -> ListItemShape.small
                sectionVerb.size -> ListItemShape.large
                else -> ListItemShape.medium
            }

            val padding = when (it.id) {
                sectionVerb.size -> 8.dp
                else -> 2.dp
            }

            ExpandableListItem(
                iconColor = MaterialTheme.colorScheme.onSecondary,
                sectionData = it,
                onIconClick = { onPlayButtonClicked(it.nameAddress) },
                shape = shape,
                modifier = Modifier.Companion
                    .padding(bottom = padding)
            )
        }


        item {
            TheoryBlock(
                icon = Icons.Outlined.SensorOccupied,
                heading = stringResource(id = R.string.noun),
                headingColor = MaterialTheme.colorScheme.onSecondary,
                subheading = stringResource(id = R.string.noun_subheading),
            )
        }
        items(
            items = sectionNoun,
            key = { it.nameAddress }
        ) {
            val shape = when (it.id) {
                1 -> ListItemShape.small
                sectionNoun.size -> ListItemShape.large
                else -> ListItemShape.medium
            }

            val padding = when (it.id) {
                sectionNoun.size -> 8.dp
                else -> 2.dp
            }

            ExpandableListItem(
                iconColor = MaterialTheme.colorScheme.onSecondary,
                sectionData = it,
                onIconClick = { onPlayButtonClicked(it.nameAddress) },
                shape = shape,
                modifier = Modifier.Companion
                    .padding(bottom = padding)
            )
        }

        item {
            TheoryBlock(
                icon = Icons.Outlined.Scale,
                heading = stringResource(id = R.string.adjective),
                headingColor = MaterialTheme.colorScheme.onSecondary,
                subheading = stringResource(id = R.string.adjective_subheading),
            )
        }
        items(
            items = sectionAdjective,
            key = { it.nameAddress }
        ) {
            val shape = when (it.id) {
                1 -> ListItemShape.small
                sectionAdjective.size -> ListItemShape.large
                else -> ListItemShape.medium
            }

            val padding = when (it.id) {
                sectionAdjective.size -> 8.dp
                else -> 2.dp
            }

            ExpandableListItem(
                iconColor = MaterialTheme.colorScheme.onSecondary,
                sectionData = it,
                onIconClick = { onPlayButtonClicked(it.nameAddress) },
                shape = shape,
                modifier = Modifier.Companion
                    .padding(bottom = padding)
            )
        }
    }
}