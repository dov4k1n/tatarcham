package com.dov4k1n.tatarapp.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.BottomAppBarItems
import com.dov4k1n.tatarapp.data.MorphologyScreen
import com.dov4k1n.tatarapp.data.NavigationDrawerItems
import com.dov4k1n.tatarapp.ui.screens.CultureScreen
import com.dov4k1n.tatarapp.ui.screens.LexiconScreen
import com.dov4k1n.tatarapp.ui.screens.MorphologyScreen
import com.dov4k1n.tatarapp.ui.screens.NounPluralScreen
import com.dov4k1n.tatarapp.ui.screens.PhoneticsScreen
import com.dov4k1n.tatarapp.ui.screens.SomeScreen
import com.dov4k1n.tatarapp.ui.screens.SyntaxScreen
import com.dov4k1n.tatarapp.ui.screens.VerbDefiniteFutureScreen
import com.dov4k1n.tatarapp.ui.screens.VerbDefinitePastScreen
import com.dov4k1n.tatarapp.ui.screens.VerbFutureInThePastScreen
import com.dov4k1n.tatarapp.ui.screens.VerbGerundScreen
import com.dov4k1n.tatarapp.ui.screens.VerbIndefiniteFutureScreen
import com.dov4k1n.tatarapp.ui.screens.VerbIndefinitePastScreen
import com.dov4k1n.tatarapp.ui.screens.VerbInfinitiveScreen
import com.dov4k1n.tatarapp.ui.screens.VerbPastContinuousScreen
import com.dov4k1n.tatarapp.ui.screens.VerbPastPerfectScreen
import com.dov4k1n.tatarapp.ui.screens.VerbPresentScreen

@Composable
fun TabsNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomAppBarItems.Phonetics.route,
        enterTransition = {
            scaleIn(initialScale = 0.9f) + fadeIn()
        },
        exitTransition = {
            scaleOut(targetScale = 0.9f) + fadeOut()
        },
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = BottomAppBarItems.Phonetics.route) {
            PhoneticsScreen()
        }
        composable(route = BottomAppBarItems.Lexicon.route) {
            LexiconScreen()
        }
        composable(route = BottomAppBarItems.Morphology.route) {
            val screenMap = hashMapOf(
                R.string.verb_present to MorphologyScreen.Present,
                R.string.verb_definite_past to MorphologyScreen.DefinitePast,
                R.string.verb_indefinite_past to MorphologyScreen.IndefinitePast,
                R.string.verb_past_continuous to MorphologyScreen.PastContinuous,
                R.string.verb_past_perfect to MorphologyScreen.PastPerfect,
                R.string.verb_definite_future to MorphologyScreen.DefiniteFuture,
                R.string.verb_indefinite_future to MorphologyScreen.IndefiniteFuture,
                R.string.verb_infinitive to MorphologyScreen.Infinitive,
                R.string.verb_gerund to MorphologyScreen.Gerund,
                R.string.verb_future_in_the_past to MorphologyScreen.FutureInThePast,
                R.string.noun_plural to MorphologyScreen.Plural,
            )

            MorphologyScreen(
                onPlayButtonClicked = { selectedLevelAddress: Int ->
                    val screen = screenMap[selectedLevelAddress]
                    if (screen != null) {
                        navController.navigate(screen.name) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
        MorphologyScreen.entries.forEach { screen ->
            composable(route = screen.name) {
                when (screen) {
                    MorphologyScreen.Present -> VerbPresentScreen()
                    MorphologyScreen.DefinitePast -> VerbDefinitePastScreen()
                    MorphologyScreen.IndefinitePast -> VerbIndefinitePastScreen()
                    MorphologyScreen.PastContinuous -> VerbPastContinuousScreen()
                    MorphologyScreen.PastPerfect -> VerbPastPerfectScreen()
                    MorphologyScreen.DefiniteFuture -> VerbDefiniteFutureScreen()
                    MorphologyScreen.IndefiniteFuture -> VerbIndefiniteFutureScreen()
                    MorphologyScreen.Infinitive -> VerbInfinitiveScreen()
                    MorphologyScreen.Gerund -> VerbGerundScreen()
                    MorphologyScreen.FutureInThePast -> VerbFutureInThePastScreen()
                    MorphologyScreen.Plural -> NounPluralScreen()
                    else -> { }
                }
            }
        }
        composable(route = BottomAppBarItems.Syntax.route) {
            SyntaxScreen()
        }
        composable(route = BottomAppBarItems.Culture.route) {
            CultureScreen()
        }
        composable(route = NavigationDrawerItems.Profile.route) {
            SomeScreen(
                name = stringResource(NavigationDrawerItems.Profile.title)
            )
        }
        composable(route = NavigationDrawerItems.Statistics.route) {
            SomeScreen(
                name = stringResource(NavigationDrawerItems.Statistics.title)
            )
        }
        composable(route = NavigationDrawerItems.Web.route) {
            SomeScreen(
                name = stringResource(NavigationDrawerItems.Web.title)
            )
        }
        composable(route = NavigationDrawerItems.Community.route) {
            SomeScreen(
                name = stringResource(NavigationDrawerItems.Community.title)
            )
        }
        composable(route = NavigationDrawerItems.Settings.route) {
            SomeScreen(
                name = stringResource(NavigationDrawerItems.Settings.title)
            )
        }
        composable(route = NavigationDrawerItems.InviteFriends.route) {
            SomeScreen(
                name = stringResource(NavigationDrawerItems.InviteFriends.title)
            )
        }
        composable(route = NavigationDrawerItems.AboutApp.route) {
            SomeScreen(
                name = stringResource(NavigationDrawerItems.AboutApp.title)
            )
        }
    }
}