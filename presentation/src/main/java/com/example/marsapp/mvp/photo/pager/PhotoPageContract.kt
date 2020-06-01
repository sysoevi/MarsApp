package com.example.marsapp.mvp.photo.pager

import android.graphics.drawable.Drawable
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PhotoPageContract {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    interface View : MvpView {
        fun setupAdapter(adapter: PhotoPagerAdapter)
        fun setCounter(size: Int, position: Int)
    }

    interface Presenter{
        fun initData()
        fun pageScrolled(position: Int)
    }

    interface ItemView {
        fun setPhoto(drawable: Drawable)
    }

}