package com.dov4k1n.tatarapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.local.ThemeMode
import com.dov4k1n.tatarapp.data.navigationDrawerItemsList
import com.dov4k1n.tatarapp.ui.components.SupportDevelopersButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    themeMode: ThemeMode,
    onThemeIconClick: () -> Unit,
    scope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavHostController,
    onCommunityClick: () -> Unit,
    onWebClick: () -> Unit,
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = typography.displaySmall,
                    color = colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(3f))
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
                        tint = colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.weight(2f))

            navigationDrawerItemsList.forEach { item ->
                val selectedItem = currentDestination?.route == item.route

                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = colorScheme.primaryContainer,
                        unselectedIconColor = colorScheme.onSurfaceVariant,
                        selectedIconColor = colorScheme.onSurfaceVariant,
                        unselectedTextColor = colorScheme.primary,
                        selectedTextColor = colorScheme.primary,
                    ),
                    icon = {
                        Icon(
                            imageVector = if (selectedItem) item.selectedIcon else item.unselectedIcon,
                            contentDescription = stringResource(item.title),
                            modifier = Modifier.padding(end = 8.dp)
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
                        when (item.route) {

                            "community" -> { onCommunityClick() }

                            "web" -> { onWebClick() }

                            else -> {
                                scope.launch {
                                    drawerState.close()
                                }
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    },
                    shape = shapes.extraSmall
                )

                if (item.route == "settings") {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            Spacer(modifier = Modifier.weight(2f))

            SupportDevelopersButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}