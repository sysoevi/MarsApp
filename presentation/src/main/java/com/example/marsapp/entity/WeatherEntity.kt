package com.example.marsapp.entity

data class WeatherEntity(
    val sol: String,
    val date: String,
    val temperature: Map<String, String>,
    val windSpeed: Map<String, String>,
    val pressure: Map<String, String>
)