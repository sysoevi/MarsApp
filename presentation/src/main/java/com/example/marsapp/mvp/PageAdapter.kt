package com.example.marsapp.mvp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.marsapp.mvp.weather.WeatherFragment
import java.nio.file.WatchEvent

class PageAdapter(
    fragmentManager: FragmentManager,
    private val tabs:List<String>
) :
    FragmentStatePagerAdapter(
        fragmentManager
        , tabs.size
    ) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> return WeatherFragment()
            1 -> return PhotoFragment()
            else -> PhotoFragment()
        }
    }

    override fun getCount(): Int {
        return tabs.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs.get(position)
    }


}