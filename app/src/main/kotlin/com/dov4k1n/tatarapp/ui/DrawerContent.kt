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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.local.ThemeMode
import com.dov4k1n.tatarapp.data.navigationDrawerItemsList
import com.dov4k1n.tatarapp.ui.components.SupportDevelopersButton
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewDrawerContent() {
    TatarAppTheme(themeMode = ThemeMode.Dark) {
        DrawerContent(
            themeMode = ThemeMode.System,
            onLanguageIconClick = {},
            onThemeIconClick = {},
            scope = rememberCoroutineScope(),
            drawerState = rememberDrawerState(DrawerValue.Open),
            navController = rememberNavController(),
        )
    }
}

@Composable
fun DrawerContent(
    themeMode: ThemeMode,
    onLanguageIconClick: () -> Unit,
    onThemeIconClick: () -> Unit,
    scope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavHostController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    ModalDrawerSheet(
        drawerShape = shapes.extraSmall,
        drawerContainerColor = colorScheme.background,
        modifier = Modifier
            .width(IntrinsicSize.Max)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.weight(3f))

            Text(
                text = stringResource(id = R.string.app_name),
                style = typography.displaySmall,
                color = colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(0.5f))

            navigationDrawerItemsList.forEach { item ->
                val selectedItem = currentDestination?.route == item.route

                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = colorScheme.primaryContainer,
                        unselectedIconColor = colorScheme.onSurfaceVariant,
                        selectedIconColor = colorScheme.onSurfaceVariant,
                        unselectedTextColor = colorScheme.outlineVariant,
                        selectedTextColor = colorScheme.outlineVariant,
                    ),
                    icon = {
                        Icon(
                            imageVector =
                                if (selectedItem)
                                    item.selectedIcon
                                else
                                    item.unselectedIcon,
                            contentDescription = stringResource(item.title),
                            modifier = Modifier.padding(end = 0.dp)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(item.title),
                            style = typography.titleSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selected = selectedItem,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(item.route) {
                            popUpTo(
                                navController.graph.findStartDestination().id
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    shape = shapes.extraSmall
                )
            }

            Spacer(modifier = Modifier.weight(0.5f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = onLanguageIconClick
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Language,
                        contentDescription = null,
                        tint = colorScheme.outlineVariant,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
                IconButton(
                    onClick = onThemeIconClick,
                ) {
                    Icon(
                        imageVector = when (themeMode) {
                            ThemeMode.Light -> Icons.Outlined.LightMode
                            ThemeMode.Dark -> Icons.Outlined.DarkMode
                            ThemeMode.System -> Icons.Outlined.Android
                        },
                        contentDescription = null,
                        tint = colorScheme.outlineVariant,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            }

            SupportDevelopersButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}