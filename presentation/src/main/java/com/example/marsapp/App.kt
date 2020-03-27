package com.example.marsapp

import android.app.Application
import com.example.marsapp.di.components.ActivityComponent
import com.example.marsapp.di.components.AppComponent
import com.example.marsapp.di.components.DaggerAppComponent
import com.example.marsapp.di.components.FragmentComponent
import com.example.marsapp.di.modules.AppModule
import com.jakewharton.threetenabp.AndroidThreeTen

class App: Application() {

    private lateinit var activityComponent: ActivityComponent
    private lateinit var appComponent:AppComponent
    private lateinit var fragmentComponent: FragmentComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDagger()
        initThreeTenAbp()
    }

    private fun initThreeTenAbp() {
        AndroidThreeTen.init(this)
    }

    private fun initDagger(){
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getActivityComponent():ActivityComponent{
        if(!::activityComponent.isInitialized){
            activityComponent = appComponent.plus()
        }
        return activityComponent
    }

    fun getFragmentComponent():FragmentComponent{
        if(!::fragmentComponent.isInitialized){
            fragmentComponent = activityComponent.plus()
        }
        return fragmentComponent
    }

    companion object {
        lateinit var instance: App private set
    }


}