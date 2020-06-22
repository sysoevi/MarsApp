package com.example.data.store.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherInfoTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromMap(map: Map<String,Float>?): String{
        return gson.toJson(map)
    }

    @TypeConverter
    fun toMap(gson: String):Map<String, Float>?{
        val type = object : TypeToken<Map<String, Float>>(){}.type
        return this.gson.fromJson(gson, type)
    }

}