package com.example.data.repository

import com.example.data.exception.NetworkConnectionException
import com.example.data.factory.PhotoFactory
import com.example.data.mapper.PhotoToDto
import com.example.data.store.PhotoStore
import com.example.data.store.room.PhotoDao
import com.example.domain.repository.PhotoRepository
import com.example.lib.NetworkManager
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PhotoReposTest {

    @Mock
    private lateinit var mockPhotoStore: PhotoStore
    @Mock
    private lateinit var mockNetworkManager: NetworkManager
    @Mock
    private lateinit var mockMapper: PhotoToDto
    @Mock
    private lateinit var mockPhotoDao: PhotoDao
    private lateinit var photoRepository: PhotoRepository
    private val scheduler: Scheduler = Schedulers.trampoline()

    @Before
    fun setUp() {
        photoRepository = PhotoRepositoryImpl(
            mockPhotoStore,
            mockNetworkManager,
            mockMapper,
            mockPhotoDao,
            scheduler
        )
    }

    @Test
    fun `get list and save to db`() {
        val fakeModel = PhotoFactory.getRandomModel()
        val fakeSingle = Single.just(listOf(fakeModel))

        Mockito.`when`(mockNetworkManager.isConnected()).thenReturn(true)
        Mockito.`when`(mockPhotoStore.getPhotoList()).thenReturn(fakeSingle)
        Mockito.`when`(mockPhotoDao.getFirstPhotoInfo())
            .thenReturn(Single.just(PhotoFactory.getRandomModel()))

        photoRepository.getPhotoList().test()

        verify(mockPhotoDao).clearTable()
        verify(mockPhotoDao).saveAll(listOf(fakeModel))
    }

    @Test
    fun `get list from db when db is not empty`() {
        val fakeModel = PhotoFactory.getRandomModel()
        val fakeSingle = Single.just(listOf(fakeModel))

        Mockito.`when`(mockNetworkManager.isConnected()).thenReturn(false)
        Mockito.`when`(mockPhotoDao.getAllPhotos()).thenReturn(fakeSingle)

        photoRepository.getPhotoList()
            .test()
            .assertNoErrors()

        verify(mockPhotoDao).getAllPhotos()
    }

    @Test
    fun `if no network and empty db return exception`(){
        Mockito.`when`(mockNetworkManager.isConnected()).thenReturn(false)
        Mockito.`when`(mockPhotoDao.getAllPhotos()).thenReturn(Single.just(listOf()))

        photoRepository.getPhotoList()
            .test()
            .assertError(NetworkConnectionException::class.java)
    }


}