package com.example.marsapp.di.components

import com.example.marsapp.di.modules.InteractorsModule
import com.example.marsapp.di.modules.MapperModule
import com.example.marsapp.di.modules.ReposModule
import com.example.marsapp.di.modules.StoreModule
import com.example.marsapp.mvp.photo.PhotoFragment
import com.example.marsapp.mvp.weather.WeatherFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        InteractorsModule::class,
        MapperModule::class,
        ReposModule::class,
    StoreModule::class]
)
interface FragmentComponent {
    fun inject(fragment: WeatherFragment)
    fun inject(fragment: PhotoFragment)
}