package com.example.caffeineapp.features.splash

import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeineapp.Screens

fun NavGraphBuilder.splashGraph() {
    composable<Screens.Splash>(
        exitTransition = {
            fadeOut()
        }
    ) {
        SplashScreen()
    }
}