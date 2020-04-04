package com.example.marsapp.di.modules

import com.example.data.entity.PhotoInfo
import com.example.data.mapper.PhotoToDto
import com.example.data.mapper.WeatherToDto
import com.example.marsapp.mapper.PhotoDtoToEntity
import com.example.marsapp.mapper.WeatherDtoToEntity
import dagger.Module
import dagger.Provides

@Module
class MapperModule {
    @Provides
    fun provideWeatherToDtoMapper():WeatherToDto{
        return WeatherToDto()
    }
    @Provides
    fun provideWeatherDtoToEntityMapper():WeatherDtoToEntity{
        return WeatherDtoToEntity()
    }

    @Provides
    fun providePhotoInfoToDto():PhotoToDto{
        return PhotoToDto()
    }

    @Provides
    fun providePhotoDtoToEntity():PhotoDtoToEntity{
        return PhotoDtoToEntity()
    }
}