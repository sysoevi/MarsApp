package com.example.marsapp.di.modules

import com.example.data.store.PhotoStore
import com.example.data.store.WeatherStore
import com.example.data.store.impl.PhotoStoreImpl
import com.example.data.store.impl.WeatherStoreImpl
import com.example.data.store.retrofit.PhotoService
import com.example.data.store.retrofit.WeatherService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class StoreModule {
    @Provides
    fun provideWeatherStoreModule(weatherService: WeatherService, gson: Gson): WeatherStore {
        return WeatherStoreImpl(weatherService, gson)
    }

    @Provides
    fun providePhotoStoreModule(photoService: PhotoService, gson: Gson): PhotoStore {
        return PhotoStoreImpl(photoService, gson)
    }
}