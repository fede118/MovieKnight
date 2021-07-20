package com.section11.movieknight.dto

import com.google.gson.annotations.SerializedName
import com.section11.components.recycler.model.ViewHolderModel

class Movie constructor(
    @SerializedName("id")
    val movieId: String,
    private val title: String,
    val fullTitle: String,
    val year: String,
    val releaseState: String,
    val image: String,
    val runtimeMins: String,
    val runtimeStr: String,
    val plot: String,
    val contentRating: String,
    val imDbRating: String,
    val metacriticRating: String,
    val genres: String,
    val directors: String,
    val stars: String
) : ViewHolderModel {
    override fun getId(): String? {
        return movieId
    }

    override fun getTitle(): String {
        return title
    }

    override fun getImageUrl(): String {
        return image
    }
}
