package com.example.marsapp.di.modules

import android.app.Application
import androidx.room.Room
import com.example.data.store.room.PhotoDao
import com.example.data.store.room.WeatherDao
import com.example.data.store.room.Database
import dagger.Module
import dagger.Provides

@Module
class RoomModule(
    application: Application,
    private val database: Database =
        Room.databaseBuilder(application, Database::class.java, "mars.db").build()
) {
    @Provides
    fun provideDatabase(): Database {
        return database
    }

    @Provides
    fun provideWeatherDao(): WeatherDao {
        return database.weatherDao()
    }

    @Provides
    fun providePhotoDao(): PhotoDao {
        return database.photoDao()
    }
}