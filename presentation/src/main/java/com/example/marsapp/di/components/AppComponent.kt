package com.example.marsapp.di.components

import com.example.marsapp.App
import com.example.marsapp.di.AppScope
import com.example.marsapp.di.modules.AppModule
import dagger.Component

@AppScope
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(application: App)
    fun plus(): ActivityComponent
}