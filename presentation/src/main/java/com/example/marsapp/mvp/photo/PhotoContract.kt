package com.example.marsapp.mvp.photo

import android.graphics.drawable.Drawable
import com.example.marsapp.mvp.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PhotoContract {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    interface View : MvpView, BaseView {
        fun setupAdapter(photoAdapter: PhotoAdapter)
    }

    interface Presenter {
        fun initializeData()
    }

    interface ItemView {
        fun setPhoto(drawable: Drawable)
    }

}