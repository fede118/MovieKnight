package com.section11.movieknight.dto

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.section11.components.recycler.model.ViewHolderModel

@Entity
open class Movie constructor(
    @PrimaryKey(autoGenerate = true)
    val primaryKey: Int,
    @SerializedName("id")
    @ColumnInfo(name = "ImdbId") val movieId: String,
    @SerializedName("title")
    @ColumnInfo val movieTitle: String,
    @ColumnInfo val image: String,
    @ColumnInfo var movieType: MovieType
) : ViewHolderModel {
    override fun getId(): String? {
        return movieId
    }

    override fun getTitle(): String {
        return movieTitle
    }

    override fun getImageUrl(): String {
        return image
    }
}

enum class MovieType {
    COMING_SOON, IN_THEATERS
}
