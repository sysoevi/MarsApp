package com.example.data.dao

import com.example.data.WeatherFactory
import org.junit.Test

class WeatherDaoTes : AbstractDaoTest() {
    private val info = WeatherFactory.getRandomListModel()
    @Test
    fun insertAndGetWeatherInformation() {
        //saving data
        database.weatherDao().saveAll(info)
        database.weatherDao()
            .getAllWeatherInfo()
            .test()
            .assertValue(info)
    }

    @Test
    fun insertInfoAndGetFirst(){
        //saving data
        database.weatherDao().saveAll(info)
        database.weatherDao()
            .getFirstWeatherInfo()
            .test()
            .assertValue(info[0])
    }

    @Test
    fun insertAndClearTable(){
        database.weatherDao().saveAll(info)
        //clearing table
        database.weatherDao().clearTable()
        database.weatherDao()
            .getAllWeatherInfo()
            .test()
            .assertValue{
                return@assertValue it.isEmpty()
            }
    }
}
