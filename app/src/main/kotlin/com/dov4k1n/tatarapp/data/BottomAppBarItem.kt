package com.dov4k1n.tatarapp.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BlurOn
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.PrecisionManufacturing
import androidx.compose.material.icons.filled.Yard
import androidx.compose.material.icons.outlined.BlurOn
import androidx.compose.material.icons.outlined.Fingerprint
import androidx.compose.material.icons.outlined.GraphicEq
import androidx.compose.material.icons.outlined.PrecisionManufacturing
import androidx.compose.material.icons.outlined.Yard
import androidx.compose.ui.graphics.vector.ImageVector
import com.dov4k1n.tatarapp.R

sealed class BottomAppBarItem(
    val route: String,
    @StringRes val title: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {

    data object Phonetics : BottomAppBarItem(
        route = "phonetics",
        title = R.string.bottom_bar_phonetics,
        selectedIcon = Icons.Filled.GraphicEq,
        unselectedIcon = Icons.Outlined.GraphicEq
    )

    data object Lexicon : BottomAppBarItem(
        route = "lexicon",
        title = R.string.bottom_bar_lexicon,
        selectedIcon = Icons.Filled.Fingerprint,
        unselectedIcon = Icons.Outlined.Fingerprint
    )

    data object Morphology : BottomAppBarItem(
        route = "morphology",
        title = R.string.bottom_bar_morphology,
        selectedIcon = Icons.Filled.PrecisionManufacturing,
        unselectedIcon = Icons.Outlined.PrecisionManufacturing
    )

    data object Syntax : BottomAppBarItem(
        route = "syntax",
        title = R.string.bottom_bar_syntax,
        selectedIcon = Icons.Filled.BlurOn,
        unselectedIcon = Icons.Outlined.BlurOn
    )

    data object Culture : BottomAppBarItem(
        route = "culture",
        title = R.string.bottom_bar_culture,
        selectedIcon = Icons.Filled.Yard,
        unselectedIcon = Icons.Outlined.Yard
    )

    companion object {
        fun fromString(value: String): BottomAppBarItem = when (value) {
            "phonetics" -> Phonetics
            "lexicon" -> Lexicon
            "morphology" -> Morphology
            "syntax" -> Syntax
            "culture" -> Culture
            else -> Phonetics
        }
    }
}

val bottomAppBarTabsList = listOf(
    BottomAppBarItem.Phonetics,
    BottomAppBarItem.Lexicon,
    BottomAppBarItem.Morphology,
    BottomAppBarItem.Syntax,
    BottomAppBarItem.Culture
)