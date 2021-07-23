package com.section11.movieknight.service

import com.section11.movieknight.BuildConfig.IMDB_KEY
import com.section11.movieknight.dto.ComingSoonResponse
import com.section11.movieknight.dto.Movie
import retrofit2.http.GET
import javax.inject.Inject

class FetchComingSoonMovies @Inject constructor(
    private val comingSoonMoviesService: ComingSoonMoviesService
) {
    suspend operator fun invoke(): List<Movie> =
        comingSoonMoviesService.getComingSoonMovies().movies
}

interface ComingSoonMoviesService {
    @GET("ComingSoon/$IMDB_KEY")
    suspend fun getComingSoonMovies() : ComingSoonResponse
}
