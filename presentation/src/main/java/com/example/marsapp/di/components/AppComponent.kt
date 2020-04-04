package com.example.marsapp.di.components

import com.example.marsapp.App
import com.example.marsapp.di.AppScope
import com.example.marsapp.di.modules.AppModule
import com.example.marsapp.di.modules.RoomModule
import dagger.Component

@AppScope
@Component(
    modules = [
        AppModule::class,
        RoomModule::class
    ]
)
interface AppComponent {
    fun inject(application: App)
    fun plus(): ActivityComponent
}