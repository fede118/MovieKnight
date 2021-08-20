package com.section11.movieknight.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.section11.movieknight.dto.Movie

/**
 * MovieKnight data [RoomDatabase] to store movies fetched from API
 */
@Database(entities = [Movie::class], exportSchema = false, version = 1)
abstract class MovieKnightDatabase : RoomDatabase(), MovieDatabase {
    /**
     * Gets movie data access object
     *
     * @return [MovieDao]
     */
    abstract override fun movieDao(): MovieDao
}

/**
 * Interface to get [MovieDao]
 */
interface MovieDatabase {
    /**
     * Gets movie data acces object
     *
     * @return [MovieDao]
     */
    fun movieDao(): MovieDao
}
