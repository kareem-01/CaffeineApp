package com.example.caffeineapp.features.order

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.caffeineapp.CoffeeCub
import com.example.caffeineapp.Screens
import com.example.caffeineapp.utils.fromJson

fun NavGraphBuilder.orderGraph() {
    composable<Screens.OrderCoffee>(
    ) {
        val coffeeCub = it.toRoute<Screens.OrderCoffee>().coffeeCup.fromJson<CoffeeCub>()
        OrderCoffeeScreen(coffeeCub = coffeeCub)
    }
}