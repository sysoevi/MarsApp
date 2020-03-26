package com.example.marsapp.di.modules

import android.os.Build
import com.example.data.store.retrofit.WeatherService
import com.example.marsapp.BuildConfig
import com.example.marsapp.R
import com.example.marsapp.di.ActivityScope
import com.example.marsapp.di.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    private val API_KEY = "api_key"
    private val BASE_URL = "https://api.nasa.gov"

    @ActivityScope
    @Provides
    fun provideRetrofit(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                val url = request.url().newBuilder().addQueryParameter(
                        API_KEY,
                        BuildConfig.NASA_API_KEY
                    )
                    .build()
                it.proceed(request.newBuilder().url(url).build())
            }
        val loginInterceptor = HttpLoggingInterceptor()
        loginInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        okHttpBuilder.addInterceptor(loginInterceptor)


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

    @ActivityScope
    @Provides
    fun provideService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

}