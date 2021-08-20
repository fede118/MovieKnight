package com.section11.movieknight.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.section11.movieknight.dto.Movie
import com.section11.movieknight.dto.MovieType

/**
 * MovieKnight data access object
 */
@Dao
interface MovieDao {

    /**
     * Gets all movies saved in database
     *
     * @return list of [Movie]
     */
    @Query("SELECT * FROM movie")
    fun getAll(): List<Movie>

    /**
     * Gets all movies with the provided type
     *
     * @param movieType desired [MovieType]
     * @return list of [Movie] of the provided type
     */
    @Query("SELECT * FROM movie WHERE movieType == :movieType")
    fun getFromMovieType(movieType: MovieType): List<Movie>

    /**
     * Inserts all provided movies
     *
     * @param movies list of [Movie] to insert
     */
    @Insert
    fun insertAll(movies: List<Movie>)

    /**
     * Deletes the provided list of movies
     *
     * @param movies list of [Movie] to delete
     */
    @Delete
    fun deleteMovies(movies: List<Movie>)
}
