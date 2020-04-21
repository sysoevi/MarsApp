package com.example.marsapp.di.modules

import android.content.Context
import com.example.data.entity.WeatherInfo
import com.example.data.entity.response.ApiWeatherResponse
import com.example.data.utils.WeatherDeserializer
import com.example.marsapp.di.AppScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.reflect.Type
import javax.inject.Named

@Module
class AppModule(private val context: Context) {

    @Provides
    @Named("MainThread")
    fun provideMaintThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Named("WorkThread")
    fun provideWorkThread(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @AppScope
    fun provideContext(): Context {
        return context
    }

    @Provides
    @AppScope
    fun provideGson(): Gson {
        return GsonBuilder().registerTypeAdapter(
            ApiWeatherResponse::class.java,
            WeatherDeserializer()
        ).create()
    }
}