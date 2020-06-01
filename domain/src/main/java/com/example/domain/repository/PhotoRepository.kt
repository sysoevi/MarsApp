package com.example.domain.repository

import com.example.domain.dto.PhotoDto
import io.reactivex.Scheduler
import io.reactivex.Single

interface PhotoRepository {

    fun getPhotoList(): Single<List<PhotoDto>>
    
}