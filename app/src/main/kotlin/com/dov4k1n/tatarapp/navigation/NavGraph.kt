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
import com.dov4k1n.tatarapp.data.BottomAppBarItem
import com.dov4k1n.tatarapp.data.MorphologyScreen
import com.dov4k1n.tatarapp.data.NavigationDrawerItems
import com.dov4k1n.tatarapp.ui.screens.AboutAppScreen
import com.dov4k1n.tatarapp.ui.screens.UnderDevelopmentScreen
import com.dov4k1n.tatarapp.ui.screens.culture.CultureScreen
import com.dov4k1n.tatarapp.ui.screens.lexicon.LexiconScreen
import com.dov4k1n.tatarapp.ui.screens.morphology.MorphologyScreen
import com.dov4k1n.tatarapp.ui.screens.morphology.NounPluralScreen
import com.dov4k1n.tatarapp.ui.screens.morphology.VerbDefiniteFutureScreen
import com.dov4k1n.tatarapp.ui.screens.morphology.VerbDefinitePastScreen
import com.dov4k1n.tatarapp.ui.screens.morphology.VerbFutureInThePastScreen
import com.dov4k1n.tatarapp.ui.screens.morphology.VerbGerundScreen
import com.dov4k1n.tatarapp.ui.screens.morphology.VerbIndefiniteFutureScreen
import com.dov4k1n.tatarapp.ui.screens.morphology.VerbIndefinitePastScreen
import com.dov4k1n.tatarapp.ui.screens.morphology.VerbInfinitiveScreen
import com.dov4k1n.tatarapp.ui.screens.morphology.VerbPastContinuousScreen
import com.dov4k1n.tatarapp.ui.screens.morphology.VerbPastPerfectScreen
import com.dov4k1n.tatarapp.ui.screens.morphology.VerbPresentScreen
import com.dov4k1n.tatarapp.ui.screens.phonetics.PhoneticsScreen
import com.dov4k1n.tatarapp.ui.screens.syntax.SyntaxScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    lastTabOpen: BottomAppBarItem
) {
    NavHost(
        navController = navController,
        startDestination = lastTabOpen.route,
        enterTransition = {
            scaleIn(initialScale = 0.9f) + fadeIn()
        },
        exitTransition = {
            scaleOut(targetScale = 0.9f) + fadeOut()
        },
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = BottomAppBarItem.Phonetics.route) {
            PhoneticsScreen()
        }
        composable(route = BottomAppBarItem.Lexicon.route) {
            LexiconScreen()
        }
        composable(route = BottomAppBarItem.Morphology.route) {
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
                }
            }
        }
        composable(route = BottomAppBarItem.Syntax.route) {
            SyntaxScreen()
        }
        composable(route = BottomAppBarItem.Culture.route) {
            CultureScreen()
        }
        composable(route = NavigationDrawerItems.Statistics.route) {
            UnderDevelopmentScreen(
                name = stringResource(NavigationDrawerItems.Statistics.title)
            )
        }
        composable(route = NavigationDrawerItems.Settings.route) {
            UnderDevelopmentScreen(
                name = stringResource(NavigationDrawerItems.Settings.title)
            )
        }
        composable(route = NavigationDrawerItems.AboutApp.route) {
            AboutAppScreen()
        }
    }
}