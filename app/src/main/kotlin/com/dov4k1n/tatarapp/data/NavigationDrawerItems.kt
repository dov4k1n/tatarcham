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