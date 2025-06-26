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

package com.dov4k1n.tatarapp.ui.state

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

class ShakingState(
    private val strength: Strength,
    private val directions: Directions
) {
    val xPosition = Animatable(initialValue = 0f)

    suspend fun shake(animationDuration: Int = 50) {
        val shakeAnimationSpec: AnimationSpec<Float> = tween(animationDuration)

        when (directions) {
            Directions.LEFT -> shakeToLeft(shakeAnimationSpec)
            Directions.RIGHT -> shakeToRight(shakeAnimationSpec)
            Directions.LEFT_THEN_RIGHT -> shakeToLeftThenRight(shakeAnimationSpec)
            Directions.RIGHT_THEN_LEFT -> shakeToRightThenLeft(shakeAnimationSpec)
        }
    }

    private suspend fun shakeToLeft(shakeAnimationSpec: AnimationSpec<Float>) {
        repeat(3) {
            xPosition.animateTo(-strength.value, shakeAnimationSpec)
            xPosition.animateTo(0f, shakeAnimationSpec)
        }
    }

    private suspend fun shakeToRight(shakeAnimationSpec: AnimationSpec<Float>) {
        repeat(3) {
            xPosition.animateTo(strength.value, shakeAnimationSpec)
            xPosition.animateTo(0f, shakeAnimationSpec)
        }
    }

    private suspend fun shakeToLeftThenRight(shakeAnimationSpec: AnimationSpec<Float>) {
        repeat(3) {
            xPosition.animateTo(-strength.value, shakeAnimationSpec)
            xPosition.animateTo(0f, shakeAnimationSpec)
            xPosition.animateTo(strength.value / 2, shakeAnimationSpec)
            xPosition.animateTo(0f, shakeAnimationSpec)
        }
    }

    private suspend fun shakeToRightThenLeft(shakeAnimationSpec: AnimationSpec<Float>) {
        repeat(3) {
            xPosition.animateTo(strength.value, shakeAnimationSpec)
            xPosition.animateTo(0f, shakeAnimationSpec)
            xPosition.animateTo(-strength.value / 2, shakeAnimationSpec)
            xPosition.animateTo(0f, shakeAnimationSpec)
        }
    }

    sealed class Strength(val value: Float) {
        data object Normal : Strength(value = 17f)
        data object Strong : Strength(value = 40f)
        data class Custom(val strength: Float) : Strength(strength)
    }

    enum class Directions {
        LEFT, RIGHT, LEFT_THEN_RIGHT, RIGHT_THEN_LEFT
    }
}

@Composable
fun rememberShakingState(
    strength: ShakingState.Strength,
    directions: ShakingState.Directions
): ShakingState {
    return remember {
        ShakingState(strength, directions)
    }
}

fun Modifier.shakable(
    state: ShakingState
): Modifier {
    return this then Modifier.graphicsLayer {
        translationX = state.xPosition.value
    }
}