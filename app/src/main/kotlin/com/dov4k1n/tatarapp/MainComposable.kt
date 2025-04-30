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
    val preferencesManager = (context.applicationContext as TatarApplication).preferencesManager
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
                            },
                            onLanguageIconClick = { choseLanguage = true }
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