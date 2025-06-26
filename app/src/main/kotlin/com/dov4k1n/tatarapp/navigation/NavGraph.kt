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
import com.dov4k1n.tatarapp.ui.screens.statistics.StatisticsScreen
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
import com.dov4k1n.tatarapp.ui.screens.statistics.CultureStatsScreen
import com.dov4k1n.tatarapp.ui.screens.statistics.LexiconStatsScreen
import com.dov4k1n.tatarapp.ui.screens.statistics.MorphologyStatsScreen
import com.dov4k1n.tatarapp.ui.screens.statistics.PhoneticsStatsScreen
import com.dov4k1n.tatarapp.ui.screens.statistics.StatisticsDetailsScreen
import com.dov4k1n.tatarapp.ui.screens.statistics.SyntaxStatsScreen
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
            val screenMap = hashMapOf(
                R.string.bottom_bar_phonetics to StatisticsDetailsScreen.PhoneticsStats,
                R.string.bottom_bar_lexicon to StatisticsDetailsScreen.LexiconStats,
                R.string.bottom_bar_morphology to StatisticsDetailsScreen.MorphologyStats,
                R.string.bottom_bar_syntax to StatisticsDetailsScreen.SyntaxStats,
                R.string.bottom_bar_culture to StatisticsDetailsScreen.CultureStats,
            )

            StatisticsScreen(
                onNavigateToDetails = { titleAddress: Int ->
                    val screen = screenMap[titleAddress]
                    if (screen != null) {
                        navController.navigate(screen.name) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
        StatisticsDetailsScreen.entries.forEach { screen ->
            composable(route = screen.name) {
                when (screen) {
                    StatisticsDetailsScreen.PhoneticsStats -> PhoneticsStatsScreen()
                    StatisticsDetailsScreen.LexiconStats -> LexiconStatsScreen()
                    StatisticsDetailsScreen.MorphologyStats -> MorphologyStatsScreen()
                    StatisticsDetailsScreen.SyntaxStats -> SyntaxStatsScreen()
                    StatisticsDetailsScreen.CultureStats -> CultureStatsScreen()
                }
            }
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