package com.example.data.store.impl

import com.example.data.entity.PhotoInfo
import com.example.data.store.PhotoStore
import com.example.data.store.retrofit.PhotoService
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import javax.inject.Inject

class PhotoStoreImpl
@Inject constructor(
    private val photoService: PhotoService
) : PhotoStore {

    override fun getPhotoList(): Single<List<PhotoInfo>> {
        return photoService.getPhotos()
            .flatMap {
                Single.just(it.list)
            }
    }

}