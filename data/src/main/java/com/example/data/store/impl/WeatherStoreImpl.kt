package com.example.data.store.impl

import com.example.data.entity.WeatherInfo
import com.example.data.store.WeatherStore
import com.example.data.store.retrofit.WeatherService
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import javax.inject.Inject

private const val SOLS_KEY = "sol_keys"

class WeatherStoreImpl
@Inject constructor(
    private val weatherService: WeatherService
    , private val gson: Gson
) : WeatherStore {

    override fun getWeather(): Single<List<WeatherInfo>> {
        return weatherService.getWeather().map { parseWeatherJson(it) }
    }

    private fun parseWeatherJson(jsonObject: JsonObject): List<WeatherInfo> {
        val sols = jsonObject.getAsJsonArray(SOLS_KEY)
        val list: MutableList<WeatherInfo> = mutableListOf()
        sols.forEach {
            val sol = it.asString
            val solObject = jsonObject.getAsJsonObject(sol)
            val type = object : TypeToken<WeatherInfo>() {}.type
            val info: WeatherInfo = gson.fromJson(solObject, type)
            info.sol = sol.toInt()
            list.add(info)
        }
        return list
    }

}