package com.example.data.factory

import com.example.data.entity.WeatherInfo
import kotlin.random.Random

object WeatherFactory: AbstractFactory<WeatherInfo>() {

    private fun randomFloat(): Float = Random.nextFloat() * 1000
    private fun randomMap(): Map<String, Float> = mapOf(
        "av" to randomFloat(),
        "mn" to randomFloat(),
        "mx" to randomFloat()
    )

    override fun getRandomModel(): WeatherInfo {
        val solId = countedId
        val weatherInfo = WeatherInfo(
            "$solId",
            randomMap(),
            randomMap(),
            randomMap()
        )
        weatherInfo.sol = solId
        return weatherInfo
    }
}