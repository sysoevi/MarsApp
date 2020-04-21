package com.example.data.entity.response

import com.example.data.entity.PhotoInfo
import com.google.gson.annotations.SerializedName

data class ApiPhotoResponse(
    @SerializedName("latest_photos")
    val list: List<PhotoInfo>
)