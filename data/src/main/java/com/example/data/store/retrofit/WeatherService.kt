package com.example.data.store.retrofit

import com.example.data.entity.response.ApiWeatherResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface WeatherService {

    @GET("/insight_weather/?feedtype=json&ver=1.0")
    fun getWeather(): Single<ApiWeatherResponse>

}