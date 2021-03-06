package com.example.data.repository

import android.annotation.SuppressLint
import com.example.data.entity.PhotoInfo
import com.example.data.exception.NetworkConnectionException
import com.example.data.mapper.PhotoToDto
import com.example.data.store.PhotoStore
import com.example.data.store.room.PhotoDao
import com.example.data.store.room.WeatherDao
import com.example.domain.dto.PhotoDto
import com.example.domain.repository.PhotoRepository
import com.example.lib.NO_NETWORK_EXCEPTION
import com.example.lib.NetworkManager
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class PhotoRepositoryImpl
@Inject constructor(
    private val photoStore: PhotoStore,
    private val networkManager: NetworkManager,
    private val mapper: PhotoToDto,
    private val photoDao: PhotoDao,
    @Named("WorkThread") private val scheduler: Scheduler
) : PhotoRepository {

    override fun getPhotoList(): Single<List<PhotoDto>> {
        return Single.defer {
            if (networkManager.isConnected()) {
                photoStore.getPhotoList()
                    .doOnSuccess {
                        saveToDb(it)
                    }
            } else {
                photoDao.getAllPhotos()
                    .flatMap {
                        if (it.isEmpty()) {
                            Single.create { emitter ->
                                emitter.onError(
                                    NetworkConnectionException(NO_NETWORK_EXCEPTION)
                                )
                            }
                        } else {
                            Single.just(it)
                        }
                    }
            }
        }.map {
            mapper.map(it) }
    }

    @SuppressLint("CheckResult")
    private fun saveToDb(list: List<PhotoInfo>) {
        photoDao.getFirstPhotoInfo()
            .subscribeOn(scheduler)
            .subscribeBy(
                onSuccess = {
                    if (it.urlId != list[0].urlId) {
                        photoDao.clearTable()
                        photoDao.saveAll(list)
                    }
                },
                onError = {
                    photoDao.saveAll(list)
                }
            )
    }
}
