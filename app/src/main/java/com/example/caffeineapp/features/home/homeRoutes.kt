package com.example.caffeineapp.features.home

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeineapp.Screens

fun NavGraphBuilder.homeGraph() {
    val animationSpec = tween<IntOffset>(500)
    composable<Screens.Home>(
        enterTransition = {
            val sourceRoute = initialState.destination.route
            when (sourceRoute) {
                Screens.Splash::class.qualifiedName -> fadeIn()
                else -> {
                    slideIn(
                        animationSpec = animationSpec,
                        initialOffset = { IntOffset(-it.width, 0) }
                    )
                }
            }
        },
        exitTransition = {
            val sourceRoute = initialState.destination.route
            when (sourceRoute) {
                Screens.Splash::class.qualifiedName -> fadeOut()
                else -> {
                    slideOut(
                        targetOffset = { IntOffset(-it.width, 0) },
                        animationSpec = animationSpec
                    )
                }
            }

        }
    ) {
        HomeScreen()
    }
}