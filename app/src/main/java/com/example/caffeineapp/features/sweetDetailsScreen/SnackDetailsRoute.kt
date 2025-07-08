package com.example.caffeineapp.features.sweetDetailsScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.caffeineapp.Screens

fun NavGraphBuilder.snackDetailsScreen() {
    composable<Screens.SnackDetailsScreen> {
        val args = it.toRoute<Screens.SnackDetailsScreen>()
        SnackDetailsScreen(imageResource = args.snackImageResource)
    }
}