package com.example.marsapp.di.modules

import com.example.data.store.WeatherStore
import com.example.data.store.impl.WeatherStoreImpl
import com.example.data.store.retrofit.WeatherService
import dagger.Module
import dagger.Provides

@Module
class StoreModule {
    @Provides
    fun provideWeatherStoreModule(weatherService: WeatherService): WeatherStore{
        return WeatherStoreImpl(weatherService)
    }
}