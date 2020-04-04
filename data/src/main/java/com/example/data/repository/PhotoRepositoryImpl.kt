package com.example.data.repository

import android.annotation.SuppressLint
import com.example.data.entity.PhotoInfo
import com.example.data.mapper.PhotoToDto
import com.example.data.store.PhotoStore
import com.example.data.store.room.PhotoDao
import com.example.data.store.room.WeatherDao
import com.example.domain.dto.PhotoDto
import com.example.domain.repository.PhotoRepository
import com.example.lib.NetworkManager
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class PhotoRepositoryImpl
@Inject constructor(
    private val photoStore: PhotoStore,
    private val networkManager: NetworkManager,
    private val mapper: PhotoToDto,
    private val photoDao: PhotoDao
) : PhotoRepository {
    override fun getPhotoList(scheduler: Scheduler): Single<List<PhotoDto>> {
        return Single.defer {
            if (networkManager.isConnected()) {
                photoStore.getPhotoList()
                    .doOnSuccess { saveToDb(scheduler, it) }
            } else {
                photoDao.getAllPhotos()
                    .flatMap {
                        if (it.isEmpty()) {
                            Single.create { emitter -> emitter.onError(Throwable("No internet connection")) }
                        } else {
                            Single.just(it)
                        }
                    }
            }
        }.map { mapper.map(it) }
    }

    @SuppressLint("CheckResult")
    private fun saveToDb(scheduler: Scheduler, list: List<PhotoInfo>) {
        photoDao.getFirstPhotoInfo()
            .doOnSuccess {
                if (it.urlId != list[0].urlId) {
                    photoDao.clearTable()
                    photoDao.saveAll(list)
                }
            }.subscribeOn(scheduler)
            .subscribe()
    }
}