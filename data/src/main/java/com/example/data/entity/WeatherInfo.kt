package com.example.data.entity

data class WeatherInfo(
    val sol: String,
    val date: String,
    val temperature: HashMap<String, String>,
    val windSpeed: HashMap<String, String>,
    val pressure: HashMap<String, String>
)