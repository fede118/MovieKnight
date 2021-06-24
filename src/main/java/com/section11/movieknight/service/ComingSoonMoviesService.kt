package com.section11.movieknight.service

import com.section11.movieknight.dto.ComingSoonResponse
import io.reactivex.Observable
import retrofit2.http.GET

// todo: move this key
private const val IMDB_KEY = "k_5u3hu1Kl"

interface ComingSoonMoviesService {

    @GET("ComingSoon/$IMDB_KEY")
    fun getComingSoonMovies() : Observable<ComingSoonResponse>
}

interface ComingSoonMoviesSuspendService {

    @GET("ComingSoon/$IMDB_KEY")
    suspend fun getComingSoonMovies() : ComingSoonResponse
}

interface ComingSoonMoviesServiceCallback {
    fun handleComingSoonResult(comingSoonResponse: ComingSoonResponse)
}
