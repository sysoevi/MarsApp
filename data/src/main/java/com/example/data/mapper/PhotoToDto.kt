package com.example.data.mapper

import com.example.data.entity.PhotoInfo
import com.example.domain.dto.PhotoDto
import com.example.lib.BaseMapper

class PhotoToDto: BaseMapper<PhotoInfo, PhotoDto>() {
    override fun map(entity: PhotoInfo): PhotoDto {
        return PhotoDto(entity.urlId, entity.imageUrl)
    }
}