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

package com.dov4k1n.tatarapp.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.bottomAppBarTabsList
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewTopAppBar() {
    TatarAppTheme(useDarkTheme = false) {
        TopAppBar(navController = NavHostController(LocalContext.current))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    navController: NavHostController,
    onNavigationDrawerIconClick: () -> Unit = {},
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val cantNavigateBack: Boolean = bottomAppBarTabsList.any {
        it.route == currentDestination?.route
    }

    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = typography.headlineLarge,
                )
            }
        },
        navigationIcon = {
            if (cantNavigateBack) {
                IconButton(
                    onClick = onNavigationDrawerIconClick
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = null,
                    )
                }
            } else {
                IconButton(
                   onClick = {
                       navController.navigateUp()
                   }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = colorScheme.onBackground,
            titleContentColor = colorScheme.primary,
            actionIconContentColor = colorScheme.onBackground
        ),
    )
}