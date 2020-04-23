package com.example.data.repository

import com.example.data.WeatherFactory
import com.example.data.mapper.WeatherToDto
import com.example.data.store.WeatherStore
import com.example.data.store.room.WeatherDao
import com.example.domain.repository.WeatherRepository
import com.example.lib.NetworkManager
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
class WeatherReposTest {

    @Mock
    private lateinit var weatherStore: WeatherStore
    @Mock
    private lateinit var networkManager: NetworkManager
    @Mock
    private lateinit var weatherToDto: WeatherToDto
    @Mock
    private lateinit var weatherDao: WeatherDao
    private val scheduler = Schedulers.trampoline()
    private lateinit var repository: WeatherRepository

    @Before
    fun setUp() {
        repository = WeatherRepositoryImpl(
            weatherStore,
            networkManager,
            weatherToDto,
            weatherDao,
            scheduler
        )
    }

    @Test
    fun `get list and save to db`(){
        val fakeList = WeatherFactory.getRandomListModel()
        val fakeSingle = Single.just(fakeList)

        Mockito.`when`(weatherStore.getWeather()).thenReturn(fakeSingle)
        Mockito.`when`(networkManager.isConnected()).thenReturn(true)
        Mockito.`when`(weatherDao.getFirstWeatherInfo())
            .thenReturn(Single.just(WeatherFactory.getRandomModel()))

        repository.getWeather().test()

        verify(weatherDao).clearTable()
        verify(weatherDao).saveAll(fakeList)
    }

}