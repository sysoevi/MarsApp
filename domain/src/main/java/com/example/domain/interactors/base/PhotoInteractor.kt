package com.example.domain.interactors.base

import com.example.domain.dto.PhotoDto
import io.reactivex.Single

interface PhotoInteractor {

    fun getPhotoList(): Single<List<PhotoDto>>

}