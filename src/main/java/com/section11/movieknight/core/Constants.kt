package com.section11.movieknight.core

import java.util.Calendar

object Constants {

    const val IMDB_KEY = "k_5u3hu1Kl"
    const val BASE_URL = "https://imdb-api.com/en/API/"

    const val MOVIE_KNIGHT_SHARED_PREFERENCES = "MovieKnight-SharedPreferences"

    // Cached movies will be valid for one hour
    const val MOVIES_VALIDITY_TIME_MEASURE = Calendar.HOUR
    const val MOVIES_VALIDITY_TIME_AMOUNT = 1
}