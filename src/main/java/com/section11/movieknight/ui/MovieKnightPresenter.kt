package com.section11.movieknight.ui

import com.section11.movieknight.core.SchedulerProvider
import com.section11.movieknight.dto.ComingSoonResponse
import com.section11.movieknight.interactor.MovieKnightInteractor

class MovieKnightPresenter(
    interactor: MovieKnightInteractor,
    schedulerProvider: SchedulerProvider
) {

    init {
        interactor.getComingSoonMovies()
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe(this::onComingSoonMoviesRecieved, this::onComingSoonMoviesFailure)
    }

    fun onComingSoonMoviesRecieved(comingSoonResponse: ComingSoonResponse) {
        val x = 1
    }

    fun onComingSoonMoviesFailure(throwable: Throwable) {
        val x = 1
    }
}