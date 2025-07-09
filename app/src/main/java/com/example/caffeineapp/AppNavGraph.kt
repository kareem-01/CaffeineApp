@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.caffeineapp

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.caffeineapp.features.coffeReadyScreen.coffeeReadyScreen
import com.example.caffeineapp.features.home.homeGraph
import com.example.caffeineapp.features.order.orderGraph
import com.example.caffeineapp.features.snacksScreen.snacksScreen
import com.example.caffeineapp.features.splash.splashGraph
import com.example.caffeineapp.features.sweetDetailsScreen.snackDetailsScreen

val LocalSharedElementTransitionScope = staticCompositionLocalOf<SharedTransitionScope> {
    error("No SharedTransitionScope provided")
}

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedElementTransitionScope provides this
        ) {
            NavHost(
                navController = LocalNavController.current,
                startDestination = Screens.Splash,
                modifier = modifier
            ) {
                splashGraph()
                homeGraph()
                orderGraph()
                coffeeReadyScreen()
                snacksScreen()
                snackDetailsScreen()
            }
        }
    }
}