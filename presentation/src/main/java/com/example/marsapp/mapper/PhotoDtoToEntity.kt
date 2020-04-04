package com.example.marsapp.mapper

import com.example.domain.dto.PhotoDto
import com.example.lib.BaseMapper
import com.example.marsapp.entity.PhotoEntity

class PhotoDtoToEntity: BaseMapper<PhotoDto, PhotoEntity>() {
    override fun map(entity: PhotoDto): PhotoEntity {
        return PhotoEntity(entity.urlId, entity.imageUrl)
    }
}