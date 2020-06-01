package com.example.marsapp.di.modules

import android.content.Context
import com.example.lib.FileManager
import com.example.lib.NetworkManager
import com.example.marsapp.manager.ResourceManager
import com.example.marsapp.manager.ResourceManagerImpl
import dagger.Module
import dagger.Provides

@Module
class ManagerModule {

    @Provides
    fun provideNetworkManager(context: Context):NetworkManager{
        return NetworkManager(context)
    }

    @Provides
    fun provideResourceManager(context: Context): ResourceManager{
        return ResourceManagerImpl(context)
    }

    fun provideFileManager(context: Context): FileManager{
        return FileManager(context)
    }
}