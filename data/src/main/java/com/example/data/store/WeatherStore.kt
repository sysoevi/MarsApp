package com.example.data.store

import com.example.data.entity.WeatherInfo
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit

interface WeatherStore {

    fun getWeather(): Single<List<WeatherInfo>>

}