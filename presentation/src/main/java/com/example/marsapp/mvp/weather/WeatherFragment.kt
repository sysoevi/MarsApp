package com.example.marsapp.mvp.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class WeatherFragment : Fragment(), WeatherContract.View {

    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setupRecycler() {
        TODO("Not yet implemented")
    }

}