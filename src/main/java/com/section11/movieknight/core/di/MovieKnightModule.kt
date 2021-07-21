package com.section11.movieknight.core.di

import com.google.gson.GsonBuilder
import com.section11.movieknight.core.Constants.BASE_URL
import com.section11.movieknight.service.ComingSoonMoviesService
import com.section11.movieknight.service.InTheatersMoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class MovieKnightModule {

    @Provides
    fun provideComingSoonMoviesService() : ComingSoonMoviesService {
        return createRepositoryWithDefaultTimeOut(ComingSoonMoviesService::class.java)
    }

    @Provides
    fun provideInTheatersService() : InTheatersMoviesService {
        return createRepositoryWithDefaultTimeOut(InTheatersMoviesService::class.java)
    }

    private fun <T> createRepositoryWithDefaultTimeOut(repositoryClass: Class<T>, baseUrl: String = BASE_URL): T {
        val okhttpClient = OkHttpClient()
        okhttpClient.newBuilder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create(GSON_INSTANCE))
            .build().create(repositoryClass)
    }

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        private const val DEFAULT_TIMEOUT = 10.toLong()
        private val GSON_INSTANCE = GsonBuilder()
            .serializeNulls()
            .setDateFormat(DATE_FORMAT)
            .create()
    }
}