package com.section11.movieknight.service

import com.section11.movieknight.core.Constants.IMDB_KEY
import com.section11.movieknight.dto.ComingSoonResponse
import io.reactivex.Observable
import retrofit2.http.GET

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
