package com.example.marsapp.mvp.photo.pager

import androidx.viewpager.widget.PagerAdapter
import com.example.marsapp.entity.PhotoEntity
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class PhotoPagerPresenter(
    private val list: List<PhotoEntity>,
    private val position: Int
) : PhotoPageContract.Presenter, MvpPresenter<PhotoPageContract.View>() {
    override fun initData() {
        viewState.setupAdapter(PhotoPagerAdapter(list))
        viewState.setCounter(list.size, position+1)
    }

    override fun pageScrolled(position: Int) {
        viewState.setCounter(list.size, position+1)
    }

}