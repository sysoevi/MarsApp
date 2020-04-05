package com.example.domain.interactors.impl

import com.example.domain.dto.WeatherDto
import com.example.domain.interactors.base.BaseInteractor
import com.example.domain.interactors.base.WeatherInteractor
import com.example.domain.repository.WeatherRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class WeatherInteractorImpl
@Inject constructor(
    @Named("MainThread") mainThread: Scheduler,
    @Named("WorkThread") workerThread: Scheduler,
    private val weatherRepository: WeatherRepository
) : BaseInteractor(mainThread, workerThread), WeatherInteractor {
    override fun getWeather(): Single<List<WeatherDto>> {
        return weatherRepository.getWeather()
            .subscribeOn(workerThread)
            .observeOn(mainThread)
    }
}