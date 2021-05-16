package com.section11.movieknight.interactor

import com.section11.movieknight.dto.Movie
import com.section11.movieknight.service.ComingSoonMoviesService
import com.section11.movieknight.service.InTheatersMoviesService
import io.reactivex.Observable

class ImDbMoviesInteractor(
    private val comingSoonMoviesService: ComingSoonMoviesService,
    private val inTheatersMoviesService: InTheatersMoviesService
) : MovieKnightInteractor {

    override fun getComingSoonMovies() : Observable<List<Movie>> {
        return comingSoonMoviesService.getComingSoonMovies()
            .map {
                it.movies
            }
    }

    override fun getInTheatersMovies(): Observable<List<Movie>> {
        return inTheatersMoviesService.getComingSoonMovies()
            .map {
                it.movies
            }
    }
}