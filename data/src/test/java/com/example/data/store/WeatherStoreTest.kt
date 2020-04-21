package com.example.data.store

import com.example.data.entity.response.ApiWeatherResponse
import com.example.data.store.impl.WeatherStoreImpl
import com.example.data.store.retrofit.WeatherService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherStoreTest {

    @Mock private lateinit var mockService:WeatherService
    private lateinit var store: WeatherStore


    @Before
    fun setUp(){
        store = WeatherStoreImpl(mockService)
    }

    @Test
    fun `if mock getWeather called call store method`(){
        val fakeApiResponse = ApiWeatherResponse(listOf())
        val fakeSingle = Single.just(fakeApiResponse)

        Mockito.`when`(mockService.getWeather()).thenReturn(fakeSingle)

        store.getWeather()
        verify(mockService).getWeather()
    }

}