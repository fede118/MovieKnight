package com.section11.movieknight.service

import com.section11.movieknight.dto.InTheatersResponse
import io.reactivex.Observable
import retrofit2.http.GET

// todo: move this key
private const val IMDB_KEY = "k_5u3hu1Kl"

interface InTheatersMoviesService {

    @GET("inTheaters/$IMDB_KEY")
    fun getInTheatersMovies() : Observable<InTheatersResponse>
}

interface InTheatersMoviesSuspendService {

    @GET("inTheaters/$IMDB_KEY")
    suspend fun getInTheatersMoviesCoroutine() : InTheatersResponse
}

interface InTheatersMoviesServiceCallback {
    fun handleInTheatersResult(inTheatersResponse: InTheatersResponse)
}
