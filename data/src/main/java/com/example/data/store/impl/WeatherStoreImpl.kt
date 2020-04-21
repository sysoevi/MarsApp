package com.example.data.store.impl

import com.example.data.entity.WeatherInfo
import com.example.data.store.WeatherStore
import com.example.data.store.retrofit.WeatherService
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import javax.inject.Inject

class WeatherStoreImpl
@Inject constructor(
    private val weatherService: WeatherService
) : WeatherStore {

    override fun getWeather(): Single<List<WeatherInfo>> {
        return weatherService.getWeather().flatMap {
            Single.just(it.weather)
        }
    }


}