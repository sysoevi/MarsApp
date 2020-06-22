package com.example.data.mapper

import com.example.data.entity.WeatherInfo
import com.example.domain.dto.WeatherDto
import com.example.lib.BaseMapper


class WeatherToDto: BaseMapper<WeatherInfo, WeatherDto>() {

    override fun map(entity: WeatherInfo): WeatherDto {
        val sol = entity.sol
        val date = entity.date
        val temp = entity.temperature?.let { HashMap(it) }
        val hws = entity.windSpeed?.let { HashMap(it) }
        val pre = entity.pressure?.let { HashMap(it) }
        return WeatherDto(sol, date, temp, hws, pre)
    }
}