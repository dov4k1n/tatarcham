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

package com.dov4k1n.tatarapp.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.dov4k1n.tatarapp.R

sealed class NavigationDrawerItems(
    val route: String,
    @StringRes val title: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {

    data object Statistics : NavigationDrawerItems(
        route = "statistics",
        title = R.string.statistics,
        selectedIcon = Icons.Filled.BarChart,
        unselectedIcon = Icons.Outlined.BarChart
    )
    data object Settings : NavigationDrawerItems(
        route = "settings",
        title = R.string.settings,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
    data object AboutApp : NavigationDrawerItems(
        route = "about_app",
        title = R.string.about_app,
        selectedIcon = Icons.AutoMirrored.Filled.Help,
        unselectedIcon = Icons.AutoMirrored.Outlined.HelpOutline
    )
}

val navigationDrawerItemsList = listOf(
    NavigationDrawerItems.Statistics,
    NavigationDrawerItems.Settings,
    NavigationDrawerItems.AboutApp,
)