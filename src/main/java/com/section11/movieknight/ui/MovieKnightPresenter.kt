package com.section11.movieknight.ui

import android.annotation.SuppressLint
import com.section11.movieknight.dto.ComingSoonResponse
import com.section11.movieknight.dto.InTheatersResponse
import com.section11.movieknight.interactor.MovieKnightInteractor
import com.section11.movieknight.service.ComingSoonMoviesServiceCallback
import com.section11.movieknight.service.InTheatersMoviesServiceCallback

class MovieKnightPresenter(
    private val view: MovieKnightView,
    private val interactor: MovieKnightInteractor
) : InTheatersMoviesServiceCallback, ComingSoonMoviesServiceCallback {

    init {
        // todo: show loading indicator or skeleton while movies are fetched
        getComingSoonMovies()
        getInTheatersMovies()
    }

    @SuppressLint("CheckResult")
    private fun getComingSoonMovies() {
        interactor.getComingSoonMovies(this)
    }

    private fun getInTheatersMovies() {
        interactor.getInTheatersMovies(this)
    }

    override fun handleInTheatersResult(inTheatersResponse: InTheatersResponse) {
        if (inTheatersResponse.movies.isNullOrEmpty()) {
            // todo: show error
            view.showErrorToast("Error launching coroutine")
        } else {
            view.setNowPlayingMoviesData(inTheatersResponse.movies)
        }
    }

    override fun handleComingSoonResult(comingSoonResponse: ComingSoonResponse) {
        if (comingSoonResponse.movies.isNullOrEmpty()) {
            // todo: show error
            view.showErrorToast("Error launching coroutine")
        } else {
            view.setComingSoonMoviesData(comingSoonResponse.movies)
        }
    }

    fun onStopReached() {
        interactor.cancelPendingRequests()
    }
}