package com.example.marsapp.mvp.photo

import android.annotation.SuppressLint
import com.example.domain.dto.PhotoDto
import com.example.domain.interactors.base.PhotoInteractor
import com.example.marsapp.entity.PhotoEntity
import com.example.marsapp.mapper.PhotoDtoToEntity
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

    private lateinit var list: List<PhotoEntity>

    @SuppressLint("CheckResult")
    override fun initializeData() {
        viewState.showProgressBar()
        photoInteractor.getPhotoList()
            .subscribeBy(
                onSuccess = {
                    this.list = mapper.map(it)
                    viewState.hideProgressBar()
                    viewState.setupAdapter(PhotoAdapter(list))
                },
                onError = {
                    println(it.message)
                }
            )
    }
}