package com.example.data.store

import io.reactivex.Single

interface PhotoStore {

    fun getPhotoList(): Single<List<String>>

}