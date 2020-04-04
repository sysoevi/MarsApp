package com.example.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class PhotoInfo(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var urlId: Int,
    @ColumnInfo(name = "image_url")
    @SerializedName("img_src")
    val imageUrl: String
)