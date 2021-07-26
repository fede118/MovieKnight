package com.section11.movieknight.core

import java.util.Calendar

object Constants {

    const val DEFAULT_PLACE_HOLDER_ITEMS = 10

    const val MOVIE_KNIGHT_SHARED_PREFERENCES = "MovieKnight-SharedPreferences"

    // Cached movies will be valid for one hour
    const val MOVIES_VALIDITY_TIME_MEASURE = Calendar.HOUR
    const val MOVIES_VALIDITY_TIME_AMOUNT = 1
}
