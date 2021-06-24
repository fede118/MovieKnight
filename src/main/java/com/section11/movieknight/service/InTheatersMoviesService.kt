package com.section11.movieknight.service

import com.section11.movieknight.core.Constants
import com.section11.movieknight.dto.InTheatersResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface InTheatersMoviesService {

    @GET(Constants.IN_THEATERS_PATH)
    fun getInTheatersMovies() : Observable<InTheatersResponse>
}

interface InTheatersMoviesSuspendService {

    @GET(Constants.IN_THEATERS_PATH)
    suspend fun getInTheatersMoviesCoroutine() : InTheatersResponse
}

interface InTheatersMoviesServiceCallback {
    fun handleInTheatersResult(inTheatersResponse: InTheatersResponse)
}
