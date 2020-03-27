package com.example.marsapp.mvp.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.R
import com.example.marsapp.entity.WeatherEntity

class WeatherAdapter(private val list: List<WeatherEntity>): RecyclerView.Adapter<WeatherItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItem {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.weather_item, parent, false)
        return WeatherItem(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WeatherItem, position: Int) {
        val weatherEntity: WeatherEntity = list[position]
        holder.setDate(weatherEntity.date)
        holder.setTemperature(weatherEntity.temperature)
        holder.setWindSpeed(weatherEntity.windSpeed)
        holder.setPressure(weatherEntity.pressure)
    }
}