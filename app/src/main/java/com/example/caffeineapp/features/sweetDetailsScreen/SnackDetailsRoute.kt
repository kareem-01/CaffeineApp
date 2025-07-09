package com.example.caffeineapp.features.sweetDetailsScreen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.caffeineapp.LocalSharedElementTransitionScope
import com.example.caffeineapp.Screens

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.snackDetailsScreen() {
    composable<Screens.SnackDetailsScreen> {
        val args = it.toRoute<Screens.SnackDetailsScreen>()
        val sharedElementTransitionScope = LocalSharedElementTransitionScope.current
        sharedElementTransitionScope.SnackDetailsScreen(
            contentVisibilityScope = this@composable,
            imageResource = args.snackImageResource
        )
    }
}