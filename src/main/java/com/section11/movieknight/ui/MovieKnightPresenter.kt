package com.section11.movieknight.ui

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import com.section11.movieknight.core.SchedulerProvider
import com.section11.movieknight.dto.Movie
import com.section11.movieknight.interactor.MovieKnightInteractor

class MovieKnightPresenter(
    private val view: MovieKnightView,
    private val interactor: MovieKnightInteractor,
    private val schedulerProvider: SchedulerProvider
) {

    init {
        getComingSoonMovies()
        getInTheatersMovies()
    }

    @SuppressLint("CheckResult")
    private fun getComingSoonMovies() {
        interactor.getComingSoonMovies()
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe(this::onComingSoonMoviesReceived, this::onComingSoonMoviesFailure)
    }

    @SuppressLint("CheckResult")
    private fun getInTheatersMovies() {
        interactor.getInTheatersMovies()
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe(this::onNowPlayingMoviesReceived, this::onInTheatersMoviesFailure)
    }

    @VisibleForTesting
    fun onComingSoonMoviesReceived(comingSoonMovies: List<Movie>) {
        view.setComingSoonMoviesData(comingSoonMovies)
    }

    @VisibleForTesting
    fun onComingSoonMoviesFailure(throwable: Throwable) {
        // todo: handle this error
    }

    @VisibleForTesting
    fun onNowPlayingMoviesReceived(inTheatersMovies: List<Movie>) {
        view.setNowPlayingMoviesData(inTheatersMovies)
    }

    @VisibleForTesting
    fun onInTheatersMoviesFailure(throwable: Throwable) {
        // todo: handle this error
    }
}