package com.section11.movieknight.db

import android.content.SharedPreferences
import androidx.core.content.edit
import com.section11.movieknight.dto.Movie
import com.section11.movieknight.dto.MovieDao
import com.section11.movieknight.dto.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class MoviesLocalRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val sharedPreferences: SharedPreferences
) {

    /**
     * Fetches movies from data base with type [MovieType.COMING_SOON]
     *
     * @return the list of movies with type [MovieType.COMING_SOON]
     */
    suspend fun getComingSoonMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            movieDao.getFromMovieType(MovieType.COMING_SOON)
        }
    }

    /**
     * Fetches movies from data base with type [MovieType.IN_THEATERS]
     *
     * @return the list of movies with type [MovieType.IN_THEATERS]
     */
    suspend fun getInTheatersMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            movieDao.getFromMovieType(MovieType.IN_THEATERS)
        }
    }

    /**
     * This function deletes all the movies with the [MovieType] provided, and adds all the movies
     * provided
     *
     * @param movies the movies to be added
     * @param type type of movies to be errased from db
     */
    suspend fun replaceMoviesWithType(movies: List<Movie>, type: MovieType) {
        return withContext(Dispatchers.IO) {

            val moviesToDelete = when (type) {
                MovieType.IN_THEATERS -> getInTheatersMovies()
                MovieType.COMING_SOON -> getComingSoonMovies()
            }

            movieDao.deleteMovies(*moviesToDelete.toTypedArray())
            insertAll(movies)
            saveTimestamp()
        }
    }

    /**
     * Inserts all the [Movie] in the list of movies to the ROOM DB
     */
    suspend fun insertAll(movies: List<Movie>) {
        return withContext(Dispatchers.IO) {
            movieDao.insertAll(*movies.toTypedArray())
            saveTimestamp()
        }
    }

    fun getMoviesSavedDate(): Date? {
        val timeStamp = sharedPreferences.getLong(TIME_STAMP, ZERO)
        return if (ZERO == timeStamp) {
            null
        } else {
            Date(timeStamp)
        }
    }

    private fun saveTimestamp() {
        val timeStamp = Date().time
        sharedPreferences.edit {
            putLong(TIME_STAMP, timeStamp)
        }
    }

    companion object {
        private const val TIME_STAMP = "time_stamp"
        private const val ZERO: Long = 0
    }
}
