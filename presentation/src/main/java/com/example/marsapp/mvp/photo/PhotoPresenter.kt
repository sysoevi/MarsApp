package com.example.marsapp.mvp.photo

import android.annotation.SuppressLint
import com.example.domain.interactors.base.PhotoInteractor
import com.example.marsapp.entity.PhotoEntity
import com.example.marsapp.mapper.PhotoDtoToEntity
import com.example.marsapp.mvp.photo.recycler.PhotoAdapter
import io.reactivex.rxkotlin.subscribeBy
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class PhotoPresenter
@Inject constructor(
    private val photoInteractor: PhotoInteractor,
    private val mapper: PhotoDtoToEntity
) : PhotoContract.Presenter,
    MvpPresenter<PhotoContract.View>() {

    private var pageNum = 1

    private val list: MutableList<PhotoEntity> = mutableListOf()

    @SuppressLint("CheckResult")
    override fun loadData() {
        photoInteractor.getPhotoList(pageNum)
            .subscribeBy(
                onSuccess = {
                    if (it.isNotEmpty()) {
                        pageNum++
                        val listOfEntity = mapper.map(it)
                        if (listOfEntity != list) {
                            if (it.size != 25) {
                                viewState.lastPageWasLoaded()
                            }
                            if (list.isEmpty()) {
                                this.list.addAll(listOfEntity)
                                viewState.hideProgressBar()
                                viewState.setupAdapter(
                                    PhotoAdapter(list)
                                )
                            } else {
                                this.list.addAll(listOfEntity)
                                viewState.refreshRecycler()
                            }
                        }
                    } else {
                        viewState.lastPageWasLoaded()
                    }
                },
                onError = {
                    println(it.printStackTrace())
                }
            )
    }
}