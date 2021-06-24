package com.section11.movieknight.interactor

import com.section11.movieknight.service.ComingSoonMoviesServiceCallback
import com.section11.movieknight.service.InTheatersMoviesServiceCallback

interface MovieKnightInteractor {

    fun getComingSoonMovies(callback: ComingSoonMoviesServiceCallback)

    fun getInTheatersMovies(callback: InTheatersMoviesServiceCallback)

    fun cancelPendingRequests()

}
