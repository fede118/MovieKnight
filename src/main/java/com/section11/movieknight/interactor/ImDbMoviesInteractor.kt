package com.section11.movieknight.interactor

import com.section11.movieknight.dto.Movie
import com.section11.movieknight.service.ComingSoonMoviesService
import io.reactivex.Observable

class ImDbMoviesInteractor(private val moviesService: ComingSoonMoviesService) : MovieKnightInteractor {

    override fun getComingSoonMovies() : Observable<List<Movie>> {
        return moviesService.getComingSoonMovies()
            .map {
                it.items
            }
    }

    fun getNowInTheatersMovies() {
        // todo
    }
}