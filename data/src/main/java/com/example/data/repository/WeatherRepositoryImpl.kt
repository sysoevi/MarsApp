package com.example.data.repository

import com.example.data.store.WeatherStore
import com.example.domain.dto.WeatherDto
import com.example.domain.repository.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl
    @Inject constructor(private val weatherStore: WeatherStore): WeatherRepository {

    override fun getWeather(): Single<WeatherDto> {
        TODO("Not yet implemented")
    }

}