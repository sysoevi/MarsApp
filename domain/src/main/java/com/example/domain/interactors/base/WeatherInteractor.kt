package com.example.domain.interactors.base

import com.example.domain.dto.WeatherDto
import io.reactivex.Single

interface WeatherInteractor {
    fun getWeather(): Single<List<WeatherDto>>
}