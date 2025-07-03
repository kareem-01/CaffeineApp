package com.example.caffeineapp.utils

import kotlinx.serialization.json.Json

val json = Json { ignoreUnknownKeys = true }

inline fun <reified T> T.toJson(): String {
    return json.encodeToString(this)
}

inline fun <reified T> String.fromJson(): T {
    return json.decodeFromString(this)
}

