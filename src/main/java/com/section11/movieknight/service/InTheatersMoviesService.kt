package com.section11.movieknight.service

import com.section11.movieknight.dto.InTheatersResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface InTheatersMoviesService {
    // todo: move this key
    @GET("inTheaters/k_5u3hu1Kl")
    fun getComingSoonMovies(
    ) : Observable<InTheatersResponse>
}