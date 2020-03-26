package com.example.marsapp.manager

import android.content.Context
import com.example.marsapp.R

class ResourceManagerImpl(private val context: Context):ResourceManager {

    override fun getTabNames(): List<String> {
        val weather = context.resources.getString(R.string.weather)
        val photos = context.resources.getString(R.string.photo_from_rover)
        return listOf(weather, photos)
    }

}