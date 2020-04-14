package com.example.data.store.impl

import com.example.data.entity.PhotoInfo
import com.example.data.mapper.PhotoToDto
import com.example.data.store.PhotoStore
import com.example.data.store.retrofit.PhotoService
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import javax.inject.Inject

private const val LATEST_PHOTOS = "latest_photos"

class PhotoStoreImpl
@Inject constructor(
    private val photoService: PhotoService,
    private val gson: Gson
) : PhotoStore {

    override fun getPhotoList(): Single<List<PhotoInfo>> {
        return photoService.getPhotos().map { parseJson(it) }
    }

    private fun parseJson(jsonObject: JsonObject): List<PhotoInfo> {
        val list = mutableListOf<PhotoInfo>()
        val jsonArray = jsonObject.getAsJsonArray(LATEST_PHOTOS)
        jsonArray.forEach {
            val type = object : TypeToken<PhotoInfo>() {}.type
            val photoInfo: PhotoInfo = gson.fromJson(it.asJsonObject, type)
            list.add(photoInfo)
        }
        return list
    }
}