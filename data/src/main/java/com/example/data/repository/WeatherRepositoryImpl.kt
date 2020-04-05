package com.example.data.repository

import android.annotation.SuppressLint
import com.example.data.entity.WeatherInfo
import com.example.data.mapper.WeatherToDto
import com.example.data.store.WeatherStore
import com.example.data.store.room.WeatherDao
import com.example.domain.dto.WeatherDto
import com.example.domain.repository.WeatherRepository
import com.example.lib.NetworkManager
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class WeatherRepositoryImpl
@Inject constructor(
    private val weatherStore: WeatherStore,
    private val networkManager: NetworkManager,
    private val weatherToDto: WeatherToDto,
    private val weatherDao: WeatherDao,
    @Named("WorkThread") private val scheduler: Scheduler
) : WeatherRepository {
    override fun getWeather(): Single<List<WeatherDto>> {
        return Single.defer {
            if (networkManager.isConnected()) {
                weatherStore.getWeather()
                    .doOnSuccess { saveToDb(it) }
            } else {
                weatherDao.getAllWeatherInfo()
                    .flatMap {
                        if (it.isEmpty()) {
                            Single.create { emitter ->
                                emitter.onError(Throwable("No internet connection"))
                            }
                        } else {
                            Single.just(it)
                        }
                    }
            }
        }.map { weatherToDto.map(it) }
    }

    @SuppressLint("CheckResult")
    fun saveToDb(list: List<WeatherInfo>) {
        weatherDao.getFirstWeatherInfo()
            .doOnSuccess {
                if (it.sol != list[0].sol) {
                    weatherDao.clearTable()
                    weatherDao.saveAll(list)
                }
            }
            .doOnError {
                weatherDao.saveAll(list)
            }
            .subscribeOn(scheduler)
            .subscribe()
    }
}