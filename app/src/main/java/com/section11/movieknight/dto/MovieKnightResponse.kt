package com.section11.movieknight.dto

import com.google.gson.annotations.SerializedName

/**
 * IMDB api response to comingSoon endpoint. Consists of a [List] of [Movie]
 */
data class ComingSoonResponse constructor(@SerializedName("items") val movies : List<Movie>)

/**
 * IMDB api response to inTheaters endpoint. Consists of a [List] of [Movie]
 */
data class InTheatersResponse constructor(@SerializedName("items") val movies : List<Movie>)
