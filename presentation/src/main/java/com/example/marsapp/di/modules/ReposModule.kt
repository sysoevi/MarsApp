package com.example.marsapp.di.modules

import com.example.data.mapper.PhotoToDto
import com.example.data.mapper.WeatherToDto
import com.example.data.repository.PhotoRepositoryImpl
import com.example.data.repository.WeatherRepositoryImpl
import com.example.data.store.PhotoStore
import com.example.data.store.WeatherStore
import com.example.data.store.room.PhotoDao
import com.example.data.store.room.WeatherDao
import com.example.domain.repository.PhotoRepository
import com.example.domain.repository.WeatherRepository
import com.example.lib.NetworkManager
import dagger.Module
import dagger.Provides

@Module
class ReposModule {
    @Provides
    fun provideWeatherRepository(
        weatherStore: WeatherStore,
        networkManager: NetworkManager,
        mapper: WeatherToDto,
        weatherDao: WeatherDao
    ): WeatherRepository {
        return WeatherRepositoryImpl(weatherStore, networkManager, mapper, weatherDao)
    }

    @Provides
    fun providePhotoRepository(
        photoStore: PhotoStore,
        networkManager: NetworkManager,
        mapper: PhotoToDto,
        photoDao: PhotoDao
    ): PhotoRepository {
        return PhotoRepositoryImpl(photoStore, networkManager, mapper, photoDao)
    }
}