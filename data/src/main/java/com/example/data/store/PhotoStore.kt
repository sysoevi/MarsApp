package com.example.data.store

import com.example.data.entity.PhotoInfo
import io.reactivex.Single

interface PhotoStore {

    fun getPhotoList(): Single<List<PhotoInfo>>

}