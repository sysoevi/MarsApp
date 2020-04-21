package com.example.data.store

import com.example.data.entity.response.ApiPhotoResponse
import com.example.data.store.impl.PhotoStoreImpl
import com.example.data.store.retrofit.PhotoService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

private const val FAKE_PAGE_NUM: Int = 2

@RunWith(MockitoJUnitRunner::class)
class PhotoStoreTest {

    private lateinit var photoStore: PhotoStore

    @Mock
    private lateinit var mockService: PhotoService

    @Before
    fun setUp() {
        photoStore = PhotoStoreImpl(photoService = mockService)
    }

    @Test
    fun `if getPhotoList called call getPhotos`() {
        val fakeResponse = ApiPhotoResponse(listOf())
        val fakeSingle = Single.just(fakeResponse)

        Mockito.`when`(mockService.getPhotos(FAKE_PAGE_NUM)).thenReturn(fakeSingle)

        photoStore.getPhotoList(FAKE_PAGE_NUM)
        verify(mockService).getPhotos(FAKE_PAGE_NUM)
    }




}