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

    private val list: MutableList<PhotoEntity> = mutableListOf()
    private var isDownloading: Boolean = false

    @SuppressLint("CheckResult")
    override fun loadData() {
        if(list.isEmpty() && !isDownloading){
            isDownloading = true
            photoInteractor.getPhotoList()
                .subscribeBy(
                    onSuccess = {
                        if(it.isNotEmpty()){
                            isDownloading = false
                            viewState.hideProgressBar()
                            list.addAll(mapper.map(it))
                            viewState.setupAdapter(PhotoAdapter(list))
                        }
                    },
                    onError = {
                        isDownloading = false
                        println(it.message)
                    }
                )
        }
    }
}