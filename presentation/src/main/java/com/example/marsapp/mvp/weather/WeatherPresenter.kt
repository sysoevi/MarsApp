package com.example.marsapp.mvp.weather

import android.annotation.SuppressLint
import com.example.domain.interactors.base.WeatherInteractor
import com.example.marsapp.entity.WeatherEntity
import com.example.marsapp.mapper.WeatherDtoToEntity
import io.reactivex.rxkotlin.subscribeBy
import moxy.MvpPresenter
import javax.inject.Inject

class WeatherPresenter
@Inject constructor(
    private val weatherInteractor: WeatherInteractor,
    private val dtoToEntity: WeatherDtoToEntity
) : MvpPresenter<WeatherContract.View>(), WeatherContract.Prsenter {

    private lateinit var weathers: List<WeatherEntity>

    @SuppressLint("CheckResult")
    override fun initializeData() {
        weatherInteractor.getWeather()
            .subscribeBy(
                onSuccess = {
                    weathers = dtoToEntity.map(it)
                    viewState.setupRecycler(WeatherAdapter(weathers))
                },
                onError = {
                    println(it.message)
                }
            )
    }

}