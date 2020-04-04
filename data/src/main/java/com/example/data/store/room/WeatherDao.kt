package com.example.data.store.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.WeatherInfo
import io.reactivex.Single

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weatherinfo")
    fun getAllWeatherInfo(): Single<List<WeatherInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(list: List<WeatherInfo>)
}