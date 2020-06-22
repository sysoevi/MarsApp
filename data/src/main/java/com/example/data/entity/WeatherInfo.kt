package com.example.data.entity

import androidx.room.*
import com.example.data.store.room.WeatherInfoTypeConverter
import com.google.gson.annotations.SerializedName

@Entity
@TypeConverters(WeatherInfoTypeConverter::class)
data class WeatherInfo(
    @ColumnInfo(name = "date")
    @SerializedName("First_UTC")
    val date: String?,
    @ColumnInfo(name = "temperature")
    @SerializedName("AT")
    val temperature: Map<String, Float>?,
    @ColumnInfo(name = "wind_speed")
    @SerializedName("HWS")
    val windSpeed: Map<String, Float>?,
    @ColumnInfo(name = "pressure")
    @SerializedName("PRE")
    val pressure: Map<String, Float>?
) {
    @PrimaryKey
    @ColumnInfo(name = "sol")
    var sol: Int = 0
}