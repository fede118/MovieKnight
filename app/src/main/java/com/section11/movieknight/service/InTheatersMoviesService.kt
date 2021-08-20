package com.section11.movieknight.service

import com.section11.movieknight.BuildConfig.IMDB_KEY
import com.section11.movieknight.dto.InTheatersResponse
import com.section11.movieknight.dto.Movie
import retrofit2.http.GET
import javax.inject.Inject

/**
 * Class in charge of getting in theaters movies
 *
 * @param inTheatersMoviesService [InTheatersMoviesService]
 */
class FetchInTheatersMovies @Inject constructor(
    private val inTheatersMoviesService: InTheatersMoviesService
) {
    /**
     * Invoke method to fetch movies
     */
    suspend operator fun invoke(): List<Movie> =
        inTheatersMoviesService.getInTheatersMovies().movies
}

/**
 * IMDB service to fetch in theaters movies
 */
interface InTheatersMoviesService {

    /**
     * Method to get all in theaters movies from IMDB
     *
     * @return [InTheatersResponse]
     */
    @GET("inTheaters/$IMDB_KEY")
    suspend fun getInTheatersMovies() : InTheatersResponse
}
