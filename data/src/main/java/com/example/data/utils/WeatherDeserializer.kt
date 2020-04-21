package com.example.data.utils

import com.example.data.entity.WeatherInfo
import com.example.data.entity.response.ApiWeatherResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

private const val SOLS_KEY = "sol_keys"

class WeatherDeserializer : JsonDeserializer<ApiWeatherResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ApiWeatherResponse {
        val list = mutableListOf<WeatherInfo>()
        if (json != null) {
            val jsonObject = json.asJsonObject
            val sols = jsonObject.getAsJsonArray(SOLS_KEY)
            val type = object : TypeToken<WeatherInfo>() {}.type
            sols.forEach { jsonElement ->
                val sol = jsonElement.asString
                val solObject = jsonObject.getAsJsonObject(sol)
                val info: WeatherInfo? = context?.deserialize(solObject, type)
                info?.let {
                    it.sol = sol.toInt()
                    list.add(it)
                }
            }
        }
        return ApiWeatherResponse(list)
    }
}