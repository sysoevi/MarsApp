package com.example.marsapp.mapper

import com.example.domain.dto.WeatherDto
import com.example.lib.BaseMapper
import com.example.marsapp.entity.WeatherEntity

class WeatherDtoToEntity: BaseMapper<WeatherDto, WeatherEntity>() {
    override fun map(entity: WeatherDto): WeatherEntity {
        val sol = entity.sol
        val date = entity.date
        val temp = HashMap(entity.temperature)
        val hws = HashMap(entity.windSpeed)
        val pre = HashMap(entity.pressure)
        return WeatherEntity(sol, date, temp, hws, pre)
    }
}