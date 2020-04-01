package com.example.domain.interactors.impl

import com.example.domain.interactors.base.BaseInteractor
import com.example.domain.interactors.base.PhotoInteractor
import com.example.domain.repository.PhotoRepository
import io.reactivex.Scheduler
import io.reactivex.Single

import javax.inject.Inject
import javax.inject.Named

class PhotoInteractorImpl
@Inject
constructor(
    @Named("MainThread") mainThread: Scheduler,
    @Named("WorkThread") workerThread: Scheduler,
    private val photoRepository: PhotoRepository
):BaseInteractor(mainThread, workerThread), PhotoInteractor{
    override fun getPhotoList(): Single<List<String>> {
        return photoRepository.getPhotoList()
            .subscribeOn(workerThread)
            .observeOn(mainThread)
    }
}