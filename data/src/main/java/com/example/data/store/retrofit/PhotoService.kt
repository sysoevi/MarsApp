package com.example.data.store.retrofit

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.GET

interface PhotoService {

    @GET("/mars-photos/api/v1/rovers/curiosity/latest_photos")
    fun getPhotos(): Single<JsonObject>

}