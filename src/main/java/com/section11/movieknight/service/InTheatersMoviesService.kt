package com.section11.movieknight.service

import com.section11.movieknight.core.Constants.IMDB_KEY
import com.section11.movieknight.dto.InTheatersResponse
import io.reactivex.Observable
import retrofit2.http.GET

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
