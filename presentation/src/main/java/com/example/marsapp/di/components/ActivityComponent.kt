package com.example.marsapp.di.components

import com.example.marsapp.di.ActivityScope
import com.example.marsapp.di.modules.ApiModule
import com.example.marsapp.di.modules.ManagerModule
import com.example.marsapp.di.modules.RoomModule
import com.example.marsapp.mvp.activity.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        ApiModule::class,
        ManagerModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun plus(): FragmentComponent
}