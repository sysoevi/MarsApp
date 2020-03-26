package com.example.marsapp.mvp.activity

import androidx.fragment.app.FragmentManager
import com.example.marsapp.mvp.PageAdapter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainContract {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    interface View: MvpView{
        fun showTabs(pageAdapter: PageAdapter)
    }

    interface Presenter{
        fun initData(fragmentManager: FragmentManager)
    }

}