package com.example.caffeineapp.features.snacksScreen

import androidx.compose.animation.fadeIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeineapp.Screens

fun NavGraphBuilder.snacksScreen() {
    composable<Screens.SnacksScreen>(enterTransition = { fadeIn() }) {
        SnacksScreen()
    }
}