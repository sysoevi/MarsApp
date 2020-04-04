package com.example.marsapp.di.modules

import com.example.domain.interactors.base.PhotoInteractor
import com.example.domain.interactors.base.WeatherInteractor
import com.example.domain.interactors.impl.PhotoInteractorImpl
import com.example.domain.interactors.impl.WeatherInteractorImpl
import com.example.domain.repository.PhotoRepository
import com.example.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class InteractorsModule {

    @Provides
    fun provideWeatherInteractor(
        @Named("MainThread") mainThread: Scheduler,
        @Named("WorkThread") workThread: Scheduler,
        @Named("WorkThread") threadForSecondOperation: Scheduler,
        weatherRepository: WeatherRepository
    ): WeatherInteractor {
        return WeatherInteractorImpl(
            mainThread,
            workThread,
            threadForSecondOperation,
            weatherRepository
        )
    }

    @Provides
    fun providePhotoInteractor(
        @Named("MainThread") mainThread: Scheduler,
        @Named("WorkThread") workThread: Scheduler,
        @Named("WorkThread") threadForSecondOperation: Scheduler,
        photoRepository: PhotoRepository
    ): PhotoInteractor {
        return PhotoInteractorImpl(
            mainThread,
            workThread,
            threadForSecondOperation,
            photoRepository
        )
    }
}