package com.example.domain.dto

data class WeatherDto(
    val sol: Int,
    val date: String,
    val temperature: Map<String, Float>,
    val windSpeed: Map<String, Float>,
    val pressure: Map<String, Float>
)