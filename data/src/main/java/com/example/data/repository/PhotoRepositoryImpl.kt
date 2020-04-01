package com.example.data.repository

import com.example.data.store.PhotoStore
import com.example.domain.repository.PhotoRepository
import com.example.lib.NetworkManager
import io.reactivex.Single
import javax.inject.Inject

class PhotoRepositoryImpl
@Inject constructor(
    private val photoStore: PhotoStore,
    private val networkManager: NetworkManager
) : PhotoRepository {
    override fun getPhotoList(): Single<List<String>> {
        return Single.defer {
            if (networkManager.isConnected()) {
                photoStore.getPhotoList()
            } else {
                Single.create { emitter ->
                    emitter.onError(Throwable("No internet connection"))
                }
            }
        }
    }
}