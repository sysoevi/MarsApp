package com.example.data.dao

import com.example.data.PhotoFactory
import org.junit.Test

class PhotoDaoTest: AbstractDaoTest() {
    private val info = PhotoFactory.getRandomListModel()

    @Test
    fun insertAndGetAllPhotosInformation(){
        database.photoDao()
            .saveAll(info)
        database.photoDao()
            .getAllPhotos()
            .test()
            .assertValue(info)
    }

    @Test
    fun insertInfoAndGetFirst(){
        database.photoDao()
            .saveAll(info)
        database.photoDao()
            .getFirstPhotoInfo()
            .test()
            .assertValue(info[0])
    }

    @Test
    fun insertAndClearTable(){
        database.photoDao().saveAll(info)
        //clearing table
        database.photoDao().clearTable()
        database.photoDao()
            .getAllPhotos()
            .test()
            .assertValue{
                return@assertValue it.isEmpty()
            }
    }

}