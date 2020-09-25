package com.section11.movieknight.core

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DataModule {

    fun provideOkHttpClient(context: Context) : OkHttpClient {
        val cache = Cache(context.cacheDir, CACHE_SIZE.toLong())
        return OkHttpClient.Builder().cache(cache).build()
    }

    fun provideGson() : Gson {
        return GSON_INSTANCE
    }

    fun <T> createRepositoryWithTimeOut(repositoryClass: Class<T>): T {
        val okhttpClient = OkHttpClient()
        okhttpClient.newBuilder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GSON_INSTANCE))
            .build().create(repositoryClass)
        return retrofit
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