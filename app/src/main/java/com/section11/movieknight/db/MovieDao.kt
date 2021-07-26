package com.section11.movieknight.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.section11.movieknight.dto.Movie
import com.section11.movieknight.dto.MovieType

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE movieType == :movieType")
    fun getFromMovieType(movieType: MovieType): List<Movie>

    @Insert
    fun insertAll(movie: List<Movie>)

    @Delete
    fun deleteMovies(movie: List<Movie>)
}
