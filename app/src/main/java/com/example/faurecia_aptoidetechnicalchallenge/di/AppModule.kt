package com.example.faurecia_aptoidetechnicalchallenge.di

import com.example.faurecia_aptoidetechnicalchallenge.remote.Api
import com.example.faurecia_aptoidetechnicalchallenge.repo.Repository
import com.example.faurecia_aptoidetechnicalchallenge.repo.RepositoryImpl
import com.example.faurecia_aptoidetechnicalchallenge.util.CoroutineDispatcherProvider
import com.example.faurecia_aptoidetechnicalchallenge.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    @Provides
    @Singleton
    fun provideRepo(api: Api): Repository = RepositoryImpl(api)

    @Provides
    @Singleton
    fun provideDispatcherProvider() = CoroutineDispatcherProvider()
}