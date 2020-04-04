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
    @Named("WorkThread") private val threadForSecondOperation: Scheduler,
    private val weatherRepository: WeatherRepository
) : BaseInteractor(mainThread, workerThread), WeatherInteractor {
    override fun getWeather(): Single<List<WeatherDto>> {
        return weatherRepository.getWeather(threadForSecondOperation)
            .subscribeOn(workerThread)
            .observeOn(mainThread)
    }
}