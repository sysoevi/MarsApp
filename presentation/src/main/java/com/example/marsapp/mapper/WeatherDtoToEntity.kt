package com.example.marsapp.mapper

import com.example.domain.dto.WeatherDto
import com.example.lib.BaseMapper
import com.example.marsapp.entity.WeatherEntity

class WeatherDtoToEntity: BaseMapper<WeatherDto, WeatherEntity>() {
    override fun map(entity: WeatherDto): WeatherEntity {
        val sol = entity.sol
        val date = entity.date
        val temp = entity.temperature?.let { HashMap(it) }
        val hws = entity.windSpeed?.let { HashMap(it) }
        val pre = entity.pressure?.let { HashMap(it) }
        return WeatherEntity(sol, date, temp, hws, pre)
    }
}