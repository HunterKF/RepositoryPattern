package com.jaegerapps.repositorypatterndemo.di

import com.jaegerapps.repositorypatterndemo.data.remote.PizzaApi
import com.jaegerapps.repositorypatterndemo.data.repo.PhotosRepoImpl
import com.jaegerapps.repositorypatterndemo.domain.repo.PhotosRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    @Provides
    @Singleton
    fun providePizzaApi(client: OkHttpClient): PizzaApi {
        return Retrofit.Builder()
            .baseUrl("https://foodish-api.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePhotosRepo(api: PizzaApi): PhotosRepo {
        return PhotosRepoImpl(api)
    }

}