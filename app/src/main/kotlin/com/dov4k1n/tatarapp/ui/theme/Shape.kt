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

