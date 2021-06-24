package com.section11.movieknight.service

import com.section11.movieknight.core.Constants
import com.section11.movieknight.dto.ComingSoonResponse
import io.reactivex.Observable
import retrofit2.http.GET

// todo: move this key
//private const val IMDB_KEY = "k_5u3hu1Kl"

interface ComingSoonMoviesService {

    @GET(Constants.COMING_SOON_PATH)
    fun getComingSoonMovies() : Observable<ComingSoonResponse>
}

interface ComingSoonMoviesSuspendService {

    @GET(Constants.COMING_SOON_PATH)
    suspend fun getComingSoonMovies() : ComingSoonResponse
}

interface ComingSoonMoviesServiceCallback {
    fun handleComingSoonResult(comingSoonResponse: ComingSoonResponse)
}
