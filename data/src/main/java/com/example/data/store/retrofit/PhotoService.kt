package com.example.data.store.retrofit
import com.example.data.entity.PhotoInfo
import com.example.data.entity.response.ApiPhotoResponse
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET("/mars-photos/api/v1/rovers/curiosity/latest_photos")
    fun getPhotos(): Single<ApiPhotoResponse>

}