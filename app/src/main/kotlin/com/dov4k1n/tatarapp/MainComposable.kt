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

package com.dov4k1n.tatarapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dov4k1n.tatarapp.data.BottomAppBarItem
import com.dov4k1n.tatarapp.data.bottomAppBarTabsList
import com.dov4k1n.tatarapp.data.local.next
import com.dov4k1n.tatarapp.navigation.NavGraph
import com.dov4k1n.tatarapp.ui.BottomAppBar
import com.dov4k1n.tatarapp.ui.DrawerContent
import com.dov4k1n.tatarapp.ui.TopAppBar
import com.dov4k1n.tatarapp.ui.components.LanguageChooserDialog
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme
import kotlinx.coroutines.launch

@Composable
fun MainComposable(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val preferencesManager =
        (context.applicationContext as TatarApplication).preferencesManager

    var themeMode by remember {
        mutableStateOf(preferencesManager.getThemeMode())
    }
    val updateThemeMode: () -> Unit = {
        val nextTheme = themeMode.next()
        themeMode = nextTheme
        preferencesManager.saveThemeMode(nextTheme)
    }

    var lastTabOpen by remember {
        mutableStateOf(preferencesManager.getLastTabOpen())
    }
    val updateLastTabOpen: (BottomAppBarItem) -> Unit = {
        preferencesManager.saveLastTabOpen(it)
    }

    TatarAppTheme(themeMode) {

        var choseLanguage by remember { mutableStateOf(false) }
        if (choseLanguage) {
            LanguageChooserDialog(
                onDismiss = { choseLanguage = false }
            )
        }

        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(DrawerValue.Closed)

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val drawerGesturesEnabled: Boolean = bottomAppBarTabsList.any {
            it.route == currentDestination?.route
        }

        Surface(Modifier.fillMaxSize()) {
            ModalNavigationDrawer(
                gesturesEnabled = drawerGesturesEnabled,
                drawerContent = {
                    DrawerContent(
                        themeMode = themeMode,
                        onLanguageIconClick = { choseLanguage = true },
                        onThemeIconClick = { updateThemeMode() },
                        scope = scope,
                        drawerState = drawerState,
                        navController = navController,
                    )
                },
                scrimColor = Color(0xBF000000),
                drawerState = drawerState
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            navController = navController,
                            onNavigationDrawerIconClick = {
                                scope.launch { drawerState.open() }
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            navController = navController,
                            updateLastTabOpen = updateLastTabOpen
                        )
                    },
                    content = {
                        NavGraph(
                            navController,
                            it,
                            lastTabOpen
                        )
                    }
                )
            }
        }
    }
}