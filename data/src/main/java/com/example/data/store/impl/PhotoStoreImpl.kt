package com.example.data.store.impl

import com.example.data.store.PhotoStore
import com.example.data.store.retrofit.PhotoService
import com.google.gson.JsonObject
import io.reactivex.Single
import javax.inject.Inject

private const val IMG_SRC = "img_src"
private const val LATEST_PHOTOS = "latest_photos"

class PhotoStoreImpl
@Inject constructor(private val photoService: PhotoService) : PhotoStore {

    override fun getPhotoList(): Single<List<String>> {
        return photoService.getPhotos().map { parseJson(it) }
    }

    private fun parseJson(jsonObject: JsonObject): List<String> {
        val list = mutableListOf<String>()
        val jsonArray = jsonObject.getAsJsonArray(LATEST_PHOTOS)
        jsonArray.forEach {
            val jsonElement = it.asJsonObject
            val imgSrc = jsonElement.get(IMG_SRC).toString().removeSurrounding("\"")
            list.add(imgSrc)
        }
        return list
    }
}