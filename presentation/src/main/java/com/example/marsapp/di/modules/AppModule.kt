package com.example.marsapp.di.modules

import android.content.Context
import com.example.marsapp.di.AppScope
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Named("MainThread")
    fun provideMaintThread():Scheduler{
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Named("WorkThread")
    fun provideWorkThread(): Scheduler{
        return Schedulers.io()
    }

    @Provides
    @AppScope
    fun provideContext():Context{
        return context
    }
}