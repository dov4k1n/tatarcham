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