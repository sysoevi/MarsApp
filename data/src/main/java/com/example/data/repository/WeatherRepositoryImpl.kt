package com.example.data.repository

import com.example.data.mapper.WeatherToDto
import com.example.data.store.WeatherStore
import com.example.data.store.room.WeatherDao
import com.example.domain.dto.WeatherDto
import com.example.domain.repository.WeatherRepository
import com.example.lib.NetworkManager
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl
@Inject constructor(
    private val weatherStore: WeatherStore,
    private val networkManager: NetworkManager,
    private val weatherToDto: WeatherToDto,
    private val weatherDao: WeatherDao
) : WeatherRepository {
    override fun getWeather(scheduler: Scheduler): Single<List<WeatherDto>> {
        return Single.defer {
            if (networkManager.isConnected()) {
                weatherStore.getWeather()
            } else {
                weatherDao.getAllWeatherInfo()
                    .flatMap {
                        if (it.isEmpty()) {
                            Single.create { emitter ->
                                emitter.onError(Throwable("No internet connectio")) }
                        } else {
                            Single.just(it)
                        }
                    }
            }
        }.map { weatherToDto.map(it) }

    }
}