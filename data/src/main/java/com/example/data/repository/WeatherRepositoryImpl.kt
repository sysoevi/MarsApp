package com.example.data.repository

import com.example.data.mapper.WeatherToDto
import com.example.data.store.WeatherStore
import com.example.domain.dto.WeatherDto
import com.example.domain.repository.WeatherRepository
import com.example.lib.NetworkManager
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl
@Inject constructor(
    private val weatherStore: WeatherStore,
    private val networkManager: NetworkManager,
    private val weatherToDto: WeatherToDto
) : WeatherRepository {
    override fun getWeather(): Single<List<WeatherDto>> {
        return Single.defer {
            if (networkManager.isConnected()) {
                weatherStore.getWeather().map { weatherToDto.map(it) }
            } else {
                Single.create { emitter ->
                    emitter.onError(Throwable("No internet connection")) }
            }
        }
    }

}