package com.example.domain.dto

data class WeatherDto(
    val sol: String,
    val date: String,
    val temperature: Map<String, String>,
    val windSpeed: Map<String, String>,
    val pressure: Map<String, String>
)