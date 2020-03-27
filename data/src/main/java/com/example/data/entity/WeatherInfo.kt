package com.example.data.entity

data class WeatherInfo(
    val sol: String,
    val date: String,
    val temperature: Map<String, Float>,
    val windSpeed: Map<String, Float>,
    val pressure: Map<String, Float>
)