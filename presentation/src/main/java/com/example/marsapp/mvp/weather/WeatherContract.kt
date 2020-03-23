package com.example.marsapp.mvp.weather

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface WeatherContract {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    interface View:MvpView {
        fun setupRecycler()
    }

    interface Prsenter{
        fun initializeData()
    }

}