package com.example.marsapp.di.modules

import com.example.data.mapper.WeatherToDto
import com.example.data.repository.WeatherRepositoryImpl
import com.example.data.store.WeatherStore
import com.example.domain.repository.WeatherRepository
import com.example.lib.NetworkManager
import dagger.Module
import dagger.Provides

@Module
class ReposModule {
    @Provides
    fun provideWeatherRepositoryModule(
        weatherStore: WeatherStore,
        networkManager: NetworkManager,
        mapper: WeatherToDto
    ): WeatherRepository {
        return WeatherRepositoryImpl(weatherStore, networkManager, mapper)
    }
}