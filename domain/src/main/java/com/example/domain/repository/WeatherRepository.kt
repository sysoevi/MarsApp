package com.example.domain.repository

import com.example.domain.dto.WeatherDto
import io.reactivex.Scheduler
import io.reactivex.Single

interface WeatherRepository {
    fun getWeather(scheduler: Scheduler): Single<List<WeatherDto>>
}