package com.example.domain.interactors.base

import io.reactivex.Single

interface PhotoInteractor {

    fun getPhotoList(): Single<List<String>>

}