package com.section11.movieknight.core.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.section11.movieknight.BuildConfig.BASE_URL
import com.section11.movieknight.core.Constants.MOVIE_KNIGHT_SHARED_PREFERENCES
import com.section11.movieknight.db.MovieDatabase
import com.section11.movieknight.db.MovieKnightDatabase
import com.section11.movieknight.db.MoviesLocalRepository
import com.section11.movieknight.dto.MovieDao
import com.section11.movieknight.service.ComingSoonMoviesService
import com.section11.movieknight.service.InTheatersMoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

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

    @Provides
    fun provideMoviesRepository(movieDao: MovieDao, sharedPreferences: SharedPreferences) : MoviesLocalRepository {
        return MoviesLocalRepository(movieDao, sharedPreferences)
    }

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase) : MovieDao {
        return movieDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun provideMoviesDatabase(@ApplicationContext appContext: Context): MovieDatabase {
        return Room.databaseBuilder(appContext, MovieKnightDatabase::class.java, "MovieKnight-Database").build()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences(MOVIE_KNIGHT_SHARED_PREFERENCES, Context.MODE_PRIVATE)

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
