package com.example.data.mapper

import com.example.data.entity.WeatherInfo
import com.example.domain.dto.WeatherDto
import com.example.lib.BaseMapper

class WeatherToDto: BaseMapper<WeatherInfo, WeatherDto>() {

    override fun map(entity: WeatherInfo): WeatherDto {
        val sol = entity.sol
        val date = entity.date
        val temp = HashMap(entity.temperature)
        val hws = HashMap(entity.windSpeed)
        val pre = HashMap(entity.pressure)
        return WeatherDto(sol, date, temp, hws, pre)
    }

    override fun map(entityList: List<WeatherInfo>): List<WeatherDto> {
        val list: MutableList<WeatherDto> = mutableListOf()
        entityList.forEach{
            list.add(map(it))
        }
        return list
    }

}