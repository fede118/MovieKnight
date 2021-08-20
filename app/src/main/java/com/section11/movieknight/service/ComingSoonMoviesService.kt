package com.section11.movieknight.service

import com.section11.movieknight.BuildConfig.IMDB_KEY
import com.section11.movieknight.dto.ComingSoonResponse
import com.section11.movieknight.dto.Movie
import retrofit2.http.GET
import javax.inject.Inject

/**
 * Class in charge of getting coming soon movies
 *
 * @param comingSoonMoviesService [ComingSoonMoviesService]
 */
class FetchComingSoonMovies @Inject constructor(
    private val comingSoonMoviesService: ComingSoonMoviesService
) {
    /**
     * Invoke method to fetch movies
     */
    suspend operator fun invoke(): List<Movie> =
        comingSoonMoviesService.getComingSoonMovies().movies
}

/**
 * IMDB service to fetch coming soon movies
 */
interface ComingSoonMoviesService {

    /**
     * Method to get all coming soon movies from IMDB
     *
     * @return [ComingSoonResponse]
     */
    @GET("ComingSoon/$IMDB_KEY")
    suspend fun getComingSoonMovies() : ComingSoonResponse
}
