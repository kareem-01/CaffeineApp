package com.example.caffeineapp.features.snacksScreen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeineapp.LocalSharedElementTransitionScope
import com.example.caffeineapp.Screens

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.snacksScreen() {
    composable<Screens.SnacksScreen>(enterTransition = { fadeIn() }) {
        LocalSharedElementTransitionScope.current.SnacksScreen(this@composable)
    }
}