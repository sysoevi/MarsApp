package com.example.marsapp.mvp.weather

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marsapp.R
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

private const val AV = "av"
private const val MIN = "mn"
private const val MAX = "mx"
private const val STRING_FORMAT = "%.2f"
private const val UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX"
private const val NEEDED_FORMAT = "dd.MM.yyyy"

class WeatherItem(itemView: View) : RecyclerView.ViewHolder(itemView), WeatherContract.ViewItem {

    private val day: TextView = itemView.findViewById(R.id.day)
    private val minTemp: TextView = itemView.findViewById(R.id.min_temp)
    private val avgTemp: TextView = itemView.findViewById(R.id.avg_temp)
    private val maxTemp: TextView = itemView.findViewById(R.id.max_temp)
    private val minWind: TextView = itemView.findViewById(R.id.min_wind_speed)
    private val avgWind: TextView = itemView.findViewById(R.id.avg_wind_speed)
    private val maxWind: TextView = itemView.findViewById(R.id.max_wind_speed)
    private val minPre: TextView = itemView.findViewById(R.id.min_pressure)
    private val avgPre: TextView = itemView.findViewById(R.id.avg_pressure)
    private val maxPre: TextView = itemView.findViewById(R.id.max_pressure)


    override fun setDate(date: String?) {
        date?.let{
            val newDate = it.removeSurrounding("\"")
            val format = DateTimeFormatter.ofPattern(NEEDED_FORMAT)
            val localDate: LocalDate = LocalDate.parse(newDate, DateTimeFormatter.ofPattern(UTC_FORMAT))
            day.text = localDate.format(format)
        }
    }

    override fun setTemperature(tempMap: Map<String, Float>?) {
        tempMap?.let {
            minTemp.text = formattedString(it[MIN])
            avgTemp.text = formattedString(it[AV])
            maxTemp.text = formattedString(it[MAX])
        }
    }

    override fun setPressure(preMap: Map<String, Float>?) {
        preMap?.let {
            minPre.text = formattedString(it[MIN])
            avgPre.text = formattedString(it[AV])
            maxPre.text = formattedString(it[MAX])
        }
    }

    override fun setWindSpeed(windMap: Map<String, Float>?) {
        windMap?.let {
            minWind.text = formattedString(it[MIN])
            avgWind.text = formattedString(it[AV])
            maxWind.text = formattedString(it[MAX])
        }
    }

    private fun formattedString(decimal: Float?): String {
        return String.format(STRING_FORMAT, decimal)
    }
}