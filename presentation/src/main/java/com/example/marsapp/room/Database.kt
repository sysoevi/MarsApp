package com.example.marsapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entity.PhotoInfo
import com.example.data.entity.WeatherInfo
import com.example.data.store.room.PhotoDao
import com.example.data.store.room.WeatherDao

@Database(entities = [WeatherInfo::class, PhotoInfo::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun weatherDao():WeatherDao
    abstract fun photoDao():PhotoDao
}