package com.example.marsapp.mvp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.marsapp.mvp.weather.WeatherFragment
import java.nio.file.WatchEvent

class PageAdapter(
    fragmentManager: FragmentManager,
    private val numOfTabs: Int
) :
    FragmentStatePagerAdapter(
        fragmentManager
        , numOfTabs
    ) {

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> return WeatherFragment()
            else -> {
                return WeatherFragment()
            }
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }


}