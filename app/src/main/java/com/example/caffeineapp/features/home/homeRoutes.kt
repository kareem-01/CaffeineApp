package com.example.caffeineapp.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeineapp.HomeScreen
import com.example.caffeineapp.Routes

fun NavGraphBuilder.homeGraph() {
    composable<Routes.Home> {
        HomeScreen()
    }
}