package com.section11.movieknight.core

import com.google.gson.GsonBuilder
import com.section11.movieknight.service.ComingSoonMoviesService
import com.section11.movieknight.service.ComingSoonMoviesSuspendService
import com.section11.movieknight.service.InTheatersMoviesService
import com.section11.movieknight.service.InTheatersMoviesSuspendService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
    fun provideComingSoonMoviesSuspendService() : ComingSoonMoviesSuspendService {
        return createRepositoryWithDefaultTimeOut(ComingSoonMoviesSuspendService::class.java)
    }

    @Provides
    fun provideInTheatersRepository() : InTheatersMoviesService {
        return createRepositoryWithDefaultTimeOut(InTheatersMoviesService::class.java)
    }

    @Provides
    fun provideInTheatersSuspendRepository() : InTheatersMoviesSuspendService {
        return createRepositoryWithDefaultTimeOut(InTheatersMoviesSuspendService::class.java)
    }

    private fun <T> createRepositoryWithDefaultTimeOut(repositoryClass: Class<T>): T {
        val okhttpClient = OkHttpClient()
        okhttpClient.newBuilder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GSON_INSTANCE))
            .build().create(repositoryClass)
    }

    companion object {
        private const val BASE_URL = "https://imdb-api.com/en/API/"
        private const val CACHE_SIZE = 10 * 1024 * 1024
        private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        private const val DEFAULT_TIMEOUT = 10.toLong()
        private val GSON_INSTANCE = GsonBuilder()
            .serializeNulls()
            .setDateFormat(DATE_FORMAT)
            .create()
    }
}