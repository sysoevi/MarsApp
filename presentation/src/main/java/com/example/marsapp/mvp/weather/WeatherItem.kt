package com.example.marsapp.mvp.weather

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.R
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

private const val AV = "av"
private const val MIN = "mn"
private const val MAX = "mx"
private const val STRING_FORMAT = "%.2f"

class WeatherItem constructor(
    itemView: View,
    private val day: TextView = itemView.findViewById(R.id.day),
    private val minTemp: TextView = itemView.findViewById(R.id.min_temp),
    private val avgTemp: TextView = itemView.findViewById(R.id.avg_temp),
    private val maxTemp: TextView = itemView.findViewById(R.id.max_temp),
    private val minWind: TextView = itemView.findViewById(R.id.min_wind_speed),
    private val avgWind: TextView = itemView.findViewById(R.id.avg_wind_speed),
    private val maxWind: TextView = itemView.findViewById(R.id.max_wind_speed),
    private val minPre: TextView = itemView.findViewById(R.id.min_pressure),
    private val avgPre: TextView = itemView.findViewById(R.id.avg_pressure),
    private val maxPre: TextView = itemView.findViewById(R.id.max_pressure)
) : RecyclerView.ViewHolder(itemView), WeatherContract.ViewItem {

    override fun setDate(date: String) {
        val newDate = date.removeSurrounding("\"")
        val format = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val localDate: LocalDate = LocalDate.parse(newDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX"))
        day.text = localDate.format(format)
    }

    override fun setTemperature(tempMap: Map<String, Float>) {
        minTemp.text = formattedString(tempMap[MIN])
        avgTemp.text = formattedString(tempMap[AV])
        maxTemp.text = formattedString(tempMap[MAX])
    }

    override fun setPressure(preMap: Map<String, Float>) {
        minPre.text = formattedString(preMap[MIN])
        avgPre.text = formattedString(preMap[AV])
        maxPre.text = formattedString(preMap[MAX])
    }

    override fun setWindSpeed(windMap: Map<String, Float>) {
        minWind.text = formattedString(windMap[MIN])
        avgWind.text = formattedString(windMap[AV])
        maxWind.text = formattedString(windMap[MAX])
    }

    private fun formattedString(decimal: Float?): String {
        return String.format(STRING_FORMAT, decimal)
    }
}