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

package com.dov4k1n.tatarapp.ui.screens.culture

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.BottomAppBarItem
import com.dov4k1n.tatarapp.ui.components.TheoryBlock
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewCultureScreen() {
    TatarAppTheme(useDarkTheme = true) {
        CultureScreen()
    }
}

@Composable
fun CultureScreen() {
    Column(
        modifier = Modifier.Companion.padding(bottom = 8.dp)
    ) {
        LazyColumn {
            item {
                Image(
                    painter = painterResource(R.drawable.golden_horde),
                    contentDescription = null,
                    modifier = Modifier.Companion
                        .padding(horizontal = 16.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 32.dp,
                                topEnd = 32.dp,
                                bottomStart = 8.dp,
                                bottomEnd = 8.dp
                            )
                        )
                )
            }
            item {
                TheoryBlock(
                    icon = BottomAppBarItem.Culture.selectedIcon,
                    heading = stringResource(R.string.bottom_bar_culture),
                    headingColor = MaterialTheme.colorScheme.onErrorContainer,
                    subheading = stringResource(id = R.string.culture_subheading),
                    modifier = Modifier.Companion
                        .padding(bottom = 8.dp)
                )
            }
        }
    }
}