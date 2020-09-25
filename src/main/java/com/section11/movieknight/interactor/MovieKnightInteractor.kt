package com.section11.movieknight.interactor

import com.section11.movieknight.dto.ComingSoonResponse
import com.section11.movieknight.service.ComingSoonMoviesService
import io.reactivex.Observable

class MovieKnightInteractor(private val moviesService: ComingSoonMoviesService) {


    fun getComingSoonMovies() : Observable<ComingSoonResponse> {
        return moviesService.getComingSoonMovies()
    }

    fun getNowInTheatersMovies() {
        // todo
    }
}