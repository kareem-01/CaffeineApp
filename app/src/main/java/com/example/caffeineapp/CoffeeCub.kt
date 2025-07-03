package com.example.caffeineapp

import kotlinx.serialization.Serializable

@Serializable
data class CoffeeCub(
    val image: Int,
    val name: String,
)
