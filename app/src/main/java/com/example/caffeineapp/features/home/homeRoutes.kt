package com.example.caffeineapp.features.home

import androidx.compose.animation.fadeIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeineapp.Screens

fun NavGraphBuilder.homeGraph() {
    composable<Screens.Home>(
        enterTransition = { fadeIn() }
    ) {
        HomeScreen()
    }
}