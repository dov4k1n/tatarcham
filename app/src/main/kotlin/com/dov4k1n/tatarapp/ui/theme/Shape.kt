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

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// default M3 tokens
val Shapes = Shapes(
    extraSmall = RoundedCornerShape(0.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

// "Support developer" button
val SupportButtonShape = Shapes(
    small = RoundedCornerShape(8.dp)
)

// TopAppBar app icon
val AppIconShape = Shapes(
    small = RoundedCornerShape(8.dp)
)

// Conjugation Card
val ConjugationCardShape = Shapes(
    small = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
)

// levels list items. small = first element, large = last element
val ListItemShape = Shapes(
    small = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    medium = RoundedCornerShape(0.dp),
    large = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
)

