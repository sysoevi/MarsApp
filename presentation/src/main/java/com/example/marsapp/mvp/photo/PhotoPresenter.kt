package com.example.marsapp.mvp.photo

import android.annotation.SuppressLint
import com.example.domain.interactors.base.PhotoInteractor
import io.reactivex.rxkotlin.subscribeBy
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class PhotoPresenter
@Inject constructor(private val photoInteractor: PhotoInteractor) : PhotoContract.Presenter,
    MvpPresenter<PhotoContract.View>() {

    @SuppressLint("CheckResult")
    override fun initializeData() {
        photoInteractor.getPhotoList()
            .subscribeBy(
                onSuccess = {
                    viewState.setupAdapter(PhotoAdapter(it))
                },
                onError = {
                    println(it.message)
                }
            )
    }
}