package com.section11.movieknight.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.section11.movieknight.dto.Movie
import com.section11.movieknight.dto.MovieDao

@Database(entities = [Movie::class], exportSchema = false, version = 1)
abstract class MovieKnightDatabase : RoomDatabase(), MovieDatabase {
    abstract override fun movieDao(): MovieDao
}

interface MovieDatabase {
    fun movieDao(): MovieDao
}