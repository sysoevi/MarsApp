package com.example.marsapp.mvp.weather

import com.example.marsapp.entity.WeatherEntity
import com.example.marsapp.mvp.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface WeatherContract {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    interface View : MvpView, BaseView {
        fun setupRecycler(weatherAdapter: WeatherAdapter)
        fun showError(message: String)
    }

    interface Prsenter {
        fun initializeData()
    }

    interface ViewItem {
        fun setDate(date: String?)
        fun setTemperature(tempMap: Map<String, Float>?)
        fun setPressure(preMap: Map<String, Float>?)
        fun setWindSpeed(windMap: Map<String, Float>?)
    }

}