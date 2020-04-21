package com.example.marsapp.di.modules


import com.example.data.store.retrofit.PhotoService
import com.example.data.store.retrofit.WeatherService
import com.example.marsapp.BuildConfig
import com.example.marsapp.di.ActivityScope
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val API_KEY = "api_key"
    private val BASE_URL = "https://api.nasa.gov"

    @ActivityScope
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
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

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

    @Provides
    fun providePhotoService(retrofit: Retrofit): PhotoService =
        retrofit.create(PhotoService::class.java)

}