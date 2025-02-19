package com.dov4k1n.tatarapp.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.dov4k1n.tatarapp.R

sealed class BottomAppBarItems(
    val route: String,
    @StringRes val title: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {

    data object Phonetics : BottomAppBarItems(
        route = "phonetics",
        title = R.string.bottom_bar_phonetics,
        selectedIcon = Icons.Filled.GraphicEq,
        unselectedIcon = Icons.Outlined.GraphicEq
    )

    data object Lexicon : BottomAppBarItems(
        route = "lexicon",
        title = R.string.bottom_bar_lexicon,
        selectedIcon = Icons.Filled.Fingerprint,
        unselectedIcon = Icons.Outlined.Fingerprint
    )

    data object Morphology : BottomAppBarItems(
        route = "morphology",
        title = R.string.bottom_bar_morphology,
        selectedIcon = Icons.Filled.PrecisionManufacturing,
        unselectedIcon = Icons.Outlined.PrecisionManufacturing
    )

    data object Syntax : BottomAppBarItems(
        route = "syntax",
        title = R.string.bottom_bar_syntax,
        selectedIcon = Icons.Filled.BlurOn,
        unselectedIcon = Icons.Outlined.BlurOn
    )

    data object Culture : BottomAppBarItems(
        route = "culture",
        title = R.string.bottom_bar_culture,
        selectedIcon = Icons.Filled.Yard,
        unselectedIcon = Icons.Outlined.Yard
    )
}

val bottomAppBarTabsList = listOf(
    BottomAppBarItems.Phonetics,
    BottomAppBarItems.Lexicon,
    BottomAppBarItems.Morphology,
    BottomAppBarItems.Syntax,
    BottomAppBarItems.Culture
)