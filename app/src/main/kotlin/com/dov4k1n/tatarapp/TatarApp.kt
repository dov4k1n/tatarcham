package com.dov4k1n.tatarapp

import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dov4k1n.tatarapp.data.bottomAppBarTabsList
import com.dov4k1n.tatarapp.navigation.TabsNavGraph
import com.dov4k1n.tatarapp.ui.BottomAppBar
import com.dov4k1n.tatarapp.ui.DrawerContent
import com.dov4k1n.tatarapp.ui.TopAppBar
import com.dov4k1n.tatarapp.ui.components.LanguageChooserDialog
import com.dov4k1n.tatarapp.ui.components.OpenLinkDialog
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme
import kotlinx.coroutines.launch

@Composable
fun TatarApp(
    navController: NavHostController = rememberNavController()
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    var useDarkTheme by remember { mutableStateOf(isSystemInDarkTheme) }

    TatarAppTheme(useDarkTheme = useDarkTheme) {
        var openCommunityDialog by remember { mutableStateOf(false) }
        if (openCommunityDialog) {
            OpenLinkDialog(
                title = stringResource(R.string.open_telegram_channel),
                link = stringResource(R.string.telegram_channel_link),
                onDismissRequest = { openCommunityDialog = false }
            )
        }

        var openWebsiteDialog by remember { mutableStateOf(false) }
        if (openWebsiteDialog) {
            OpenLinkDialog(
                title = stringResource(R.string.open_website),
                link = stringResource(R.string.website_link),
                onDismissRequest = { openWebsiteDialog = false }
            )
        }

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

        Surface(
            Modifier.fillMaxSize()
        ) {
            ModalNavigationDrawer(
                gesturesEnabled = drawerGesturesEnabled,
                drawerContent = {
                    DrawerContent(
                        useDarkTheme = useDarkTheme,
                        onThemeIconClick = { useDarkTheme = !useDarkTheme },
                        scope = scope,
                        drawerState = drawerState,
                        navController = navController,
                        onCommunityClick = { openCommunityDialog = true },
                        onWebClick = { openWebsiteDialog = true }
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
                            },
                            onLanguageIconClick = { choseLanguage = true }
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            navController = navController
                        )
                    },
                    content = {
                        TabsNavGraph(navController, it)
                    }
                )
            }
        }
    }
}