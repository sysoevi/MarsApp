package com.example.marsapp.di.modules

import android.os.Build
import com.example.data.store.retrofit.WeatherService
import com.example.marsapp.BuildConfig
import com.example.marsapp.R
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    private val API_KEY = "api_key"
    private val BASE_URL = "https://api.nasa.gov"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                val url = request.url().newBuilder().addQueryParameter(
                        API_KEY,
                        BuildConfig.NASA_API_KEY
                    )
                    .build()
                it.proceed(request.newBuilder().url(url).build())
            }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient.build())
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

}