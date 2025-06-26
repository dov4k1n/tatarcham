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

package com.dov4k1n.tatarapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dov4k1n.tatarapp.R

val AbrilFatface = FontFamily(
    Font(R.font.abril_fatface_regular)
)

val Montserrat = FontFamily(
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_extrabold, FontWeight.ExtraBold)
)

//val textShadow = Shadow(
//    color = Color(0xFF131F24),
//    offset = Offset(4f, 8f),
//    blurRadius = 8f
//)

val Typography = Typography(
    // App name in the navigation drawer
    displaySmall = TextStyle(
        fontFamily = AbrilFatface,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    // App name on the main screens
    headlineLarge = TextStyle(
        fontFamily = AbrilFatface,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    // Current Word on Conjugation Card
    headlineSmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 28.sp
    ),
    // Theory blocks' titles
    titleLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 22.sp,
    ),
    // "Present Simple" header on the verb conjugation screen's theory
    titleMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    ),
    // "Present Simple" header on the verb conjugation screen
    titleSmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),
    // verb conjugation screen's theory headers like "Personal affixes"
    bodyLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    // theory text & levels names
    bodyMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    // profile info text & theory blocks' subheadings
    bodySmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    ),
    // bottom navigation
    labelSmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
    )
)