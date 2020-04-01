package com.example.marsapp.di.modules

import com.example.data.mapper.WeatherToDto
import com.example.data.repository.PhotoRepositoryImpl
import com.example.data.repository.WeatherRepositoryImpl
import com.example.data.store.PhotoStore
import com.example.data.store.WeatherStore
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
        mapper: WeatherToDto
    ): WeatherRepository {
        return WeatherRepositoryImpl(weatherStore, networkManager, mapper)
    }

    @Provides
    fun providePhotoRepository(
        photoStore: PhotoStore,
        networkManager: NetworkManager
    ): PhotoRepository {
        return PhotoRepositoryImpl(photoStore, networkManager)
    }
}