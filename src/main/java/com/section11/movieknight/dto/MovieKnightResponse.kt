package com.section11.movieknight.dto

import com.google.gson.annotations.SerializedName

class ComingSoonResponse constructor(
    @SerializedName("items")
    val movies : List<Movie>
)

class InTheatersResponse constructor(
    @SerializedName("items")
    val movies : List<Movie>
)