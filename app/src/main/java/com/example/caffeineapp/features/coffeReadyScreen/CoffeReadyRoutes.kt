package com.example.caffeineapp.features.coffeReadyScreen

import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeineapp.Screens

fun NavGraphBuilder.coffeeReadyScreen() {
    composable<Screens.CoffeeReadyScreen>(
        exitTransition = { fadeOut() }
    ) {
        CoffeeReadyScreen()
    }
}