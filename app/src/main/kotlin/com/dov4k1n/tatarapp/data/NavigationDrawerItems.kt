package com.dov4k1n.tatarapp.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Web
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Web
import androidx.compose.ui.graphics.vector.ImageVector
import com.dov4k1n.tatarapp.R

sealed class NavigationDrawerItems(
    val route: String,
    @StringRes val title: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    data object Profile : NavigationDrawerItems(
        route = "profile",
        title = R.string.profile,
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle
    )
    data object Statistics : NavigationDrawerItems(
        route = "statistics",
        title = R.string.statistics,
        selectedIcon = Icons.Filled.BarChart,
        unselectedIcon = Icons.Outlined.BarChart
    )
    data object Web : NavigationDrawerItems(
        route = "web",
        title = R.string.web,
        selectedIcon = Icons.Filled.Web,
        unselectedIcon = Icons.Outlined.Web
    )
    data object Community : NavigationDrawerItems(
        route = "community",
        title = R.string.community,
        selectedIcon = Icons.Filled.Forum,
        unselectedIcon = Icons.Outlined.Forum
    )
    data object Settings : NavigationDrawerItems(
        route = "settings",
        title = R.string.settings,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
    data object InviteFriends : NavigationDrawerItems(
        route = "invite_friends",
        title = R.string.invite_friends,
        selectedIcon = Icons.Filled.PersonAdd,
        unselectedIcon = Icons.Outlined.PersonAdd
    )
    data object AboutApp : NavigationDrawerItems(
        route = "about_app",
        title = R.string.about_app,
        selectedIcon = Icons.AutoMirrored.Filled.Help,
        unselectedIcon = Icons.AutoMirrored.Outlined.HelpOutline
    )
}

val navigationDrawerItemsList = listOf(
    NavigationDrawerItems.Profile,
    NavigationDrawerItems.Statistics,
    NavigationDrawerItems.AboutApp,
    NavigationDrawerItems.Settings,
    NavigationDrawerItems.InviteFriends,
    NavigationDrawerItems.Community,
//    NavigationDrawerItems.Web,
)