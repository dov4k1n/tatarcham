package com.dov4k1n.tatarapp.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dov4k1n.tatarapp.MainComposable
import com.dov4k1n.tatarapp.data.BottomAppBarItem
import com.dov4k1n.tatarapp.data.bottomAppBarTabsList

@Preview
@Composable
fun PreviewBottomAppBar() {
    MainComposable()
}

@Composable
fun BottomAppBar(
    navController: NavHostController,
    updateLastTabOpen: (BottomAppBarItem) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomAppBar: Boolean = bottomAppBarTabsList.any {
        it.route == currentDestination?.route
    }

    val selectedIconColor: Color = when(currentDestination?.route) {
        BottomAppBarItem.Phonetics.route -> colorScheme.onError
        BottomAppBarItem.Lexicon.route -> colorScheme.inversePrimary
        BottomAppBarItem.Morphology.route -> colorScheme.onSecondary
        BottomAppBarItem.Syntax.route -> colorScheme.errorContainer
        BottomAppBarItem.Culture.route -> colorScheme.onErrorContainer
        else -> colorScheme.onSecondary
    }

    if (showBottomAppBar) {
        NavigationBar(
            containerColor = colorScheme.background,
        ) {
            val colors = NavigationBarItemColors(
                selectedIconColor = selectedIconColor,
                selectedTextColor = selectedIconColor,
                selectedIndicatorColor = Color.Transparent,
                unselectedIconColor = colorScheme.outlineVariant,
                unselectedTextColor = colorScheme.outlineVariant,
                disabledIconColor = colorScheme.outlineVariant,
                disabledTextColor = colorScheme.outlineVariant
            )

            bottomAppBarTabsList.forEach { tab ->
                val selectedTab = currentDestination?.route == tab.route
                NavigationBarItem(
                    colors = colors,
                    label = {
                        Text(
                            text = stringResource(tab.title),
                            style = typography.labelSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = if (selectedTab) tab.selectedIcon else tab.unselectedIcon,
                            contentDescription = null
                        )
                    },
                    selected = selectedTab,
                    onClick = {
                        updateLastTabOpen(tab)

                        navController.navigate(tab.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}