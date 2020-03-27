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
private const val FIRST_UTC = "First_UTC"
private const val TEMP = "AT"
private const val WIND_SPEED = "HWS"
private const val PRESSURE = "PRE"

class WeatherStoreImpl
@Inject constructor(private val weatherService: WeatherService) : WeatherStore {

    override fun getWeather(): Single<List<WeatherInfo>> {
        return weatherService.getWeather().map {parseWeatherJson(it)}
    }

    private fun parseWeatherJson(jsonObject: JsonObject): List<WeatherInfo> {
        val sols = jsonObject.getAsJsonArray(SOLS_KEY)
        val list: MutableList<WeatherInfo> = mutableListOf()
        sols.forEach {
            val sol = it.asString
            val solObject = jsonObject.getAsJsonObject(sol)
            val itemType = object : TypeToken<HashMap<String, Float>>() {}.type
            val date: String = solObject.get(FIRST_UTC).toString()
            val temp: HashMap<String, Float> = Gson().fromJson(
                solObject.getAsJsonObject(TEMP).toString(), itemType
            )
            val hws: HashMap<String, Float> = Gson().fromJson(
                solObject.getAsJsonObject(WIND_SPEED).toString(), itemType
            )
            val pre: HashMap<String, Float> = Gson().fromJson(
                solObject.getAsJsonObject(PRESSURE).toString(), itemType
            )
            list.add(WeatherInfo(sol, date, temp, hws, pre))
        }
        return list
    }

}