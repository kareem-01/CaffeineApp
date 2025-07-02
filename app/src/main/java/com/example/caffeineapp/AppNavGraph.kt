package com.example.caffeineapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.caffeineapp.features.home.homeGraph
import com.example.caffeineapp.features.splash.splashGraph

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    NavHost(
        navController = LocalNavController.current,
        startDestination = Screens.Splash,
        modifier = modifier
    ) {
        splashGraph()
        homeGraph()
    }
}