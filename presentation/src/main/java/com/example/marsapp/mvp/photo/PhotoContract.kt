package com.example.marsapp.mvp.photo

import android.graphics.drawable.Drawable
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PhotoContract {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    interface View : MvpView {
        fun setupAdapter(photoAdapter: PhotoAdapter)
    }

    interface Presenter {
        fun initializeData()
    }

    interface ItemView {
        fun setPhoto(drawable: Drawable)
    }

}