package com.example.caffeineapp.features.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeineapp.Screens

fun NavGraphBuilder.splashGraph() {
    composable<Screens.Home> {
        SplashScreen()
    }
}