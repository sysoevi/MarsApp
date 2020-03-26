package com.example.marsapp.mvp.weather

import com.example.marsapp.entity.WeatherEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface WeatherContract {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    interface View:MvpView {
        fun setupRecycler(weatherList: List<WeatherEntity>)
    }

    interface Prsenter{
        fun initializeData()
    }

}