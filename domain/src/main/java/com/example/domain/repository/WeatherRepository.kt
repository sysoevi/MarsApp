package com.example.domain.repository

import com.example.domain.dto.WeatherDto
import io.reactivex.Single
import java.util.*

interface WeatherRepository {
    fun getWeather(): Single<WeatherDto>
}