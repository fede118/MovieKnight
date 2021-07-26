package com.section11.movieknight.dto

import com.google.gson.annotations.SerializedName

data class ComingSoonResponse constructor(@SerializedName("items") val movies : List<Movie>)

data class InTheatersResponse constructor(@SerializedName("items") val movies : List<Movie>)
