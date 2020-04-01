package com.example.domain.repository

import io.reactivex.Single

interface PhotoRepository {

    fun getPhotoList(): Single<List<String>>
    
}