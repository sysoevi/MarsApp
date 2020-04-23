package com.example.domain.interactors.base

import com.example.domain.dto.PhotoDto
import io.reactivex.Single

interface PhotoInteractor {

    fun getPhotoList(pageNum: Int): Single<List<PhotoDto>>

}