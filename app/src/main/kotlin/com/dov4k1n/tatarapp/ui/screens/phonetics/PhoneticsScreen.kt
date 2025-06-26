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

package com.dov4k1n.tatarapp.ui.screens.phonetics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.BottomAppBarItem
import com.dov4k1n.tatarapp.data.sectionHarmonies
import com.dov4k1n.tatarapp.ui.components.ExpandableListItem
import com.dov4k1n.tatarapp.ui.components.TheoryBlock
import com.dov4k1n.tatarapp.ui.theme.ListItemShape
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewPhoneticsScreen() {
    TatarAppTheme(useDarkTheme = true) {
        PhoneticsScreen()
    }
}

@Composable
fun PhoneticsScreen(
    onPlayButtonClicked: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier.Companion.padding(bottom = 8.dp)
    ) {
        LazyColumn {
            item {
                TheoryBlock(
                    icon = BottomAppBarItem.Phonetics.selectedIcon,
                    heading = stringResource(R.string.bottom_bar_phonetics),
                    headingColor = MaterialTheme.colorScheme.onError,
                    subheading = stringResource(id = R.string.phonetics_subheading),
                    modifier = Modifier.Companion
                        .padding(bottom = 8.dp)
                )
            }


            item {
                TheoryBlock(
                    icon = Icons.Outlined.MusicNote,
                    heading = stringResource(id = R.string.harmonies),
                    headingColor = MaterialTheme.colorScheme.onError,
                    subheading = stringResource(id = R.string.harmonies_subheading),
                    modifier = Modifier.Companion
                )
            }
            items(
                items = sectionHarmonies,
                key = { it.nameAddress }
            ) {
                val shape = when (it.id) {
                    1 -> ListItemShape.small
                    sectionHarmonies.size -> ListItemShape.large
                    else -> ListItemShape.medium
                }

                val padding = when (it.id) {
                    sectionHarmonies.size -> 8.dp
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
        Spacer(modifier = Modifier.Companion.weight(1f))
    }
}
