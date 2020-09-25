package com.section11.movieknight.service

import com.section11.movieknight.dto.ComingSoonResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ComingSoonMoviesService {

    @GET("ComingSoon/k_5u3hu1Kl")
    fun getComingSoonMovies(
    ) : Observable<ComingSoonResponse>
}
