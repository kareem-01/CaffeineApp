package com.example.caffeineapp

import kotlinx.serialization.Serializable

object Screens {

    @Serializable
    data object Splash

    @Serializable
    data object Home


    @Serializable
    data class OrderCoffee(val coffeeCup: String)

    @Serializable
    data object CoffeeReadyScreen

    @Serializable
    data object SnacksScreen

    @Serializable
    data class SnackDetailsScreen(val snackImageResource: Int)

}