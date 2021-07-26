package com.section11.movieknight.service

import com.section11.movieknight.BuildConfig.IMDB_KEY
import com.section11.movieknight.dto.InTheatersResponse
import com.section11.movieknight.dto.Movie
import retrofit2.http.GET
import javax.inject.Inject

class FetchInTheatersMovies @Inject constructor(
    private val inTheatersMoviesService: InTheatersMoviesService
) {
    suspend operator fun invoke(): List<Movie> =
        inTheatersMoviesService.getInTheatersMovies().movies
}

interface InTheatersMoviesService {

    @GET("inTheaters/$IMDB_KEY")
    suspend fun getInTheatersMovies() : InTheatersResponse
}
