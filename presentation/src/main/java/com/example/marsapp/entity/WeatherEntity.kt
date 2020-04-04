package com.example.marsapp.entity

data class WeatherEntity(
    val sol: Int,
    val date: String,
    val temperature: Map<String, Float>,
    val windSpeed: Map<String, Float>,
    val pressure: Map<String, Float>
)