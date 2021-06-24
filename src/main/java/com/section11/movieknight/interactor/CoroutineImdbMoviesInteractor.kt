package com.section11.movieknight.interactor

import com.section11.movieknight.dto.ComingSoonResponse
import com.section11.movieknight.dto.InTheatersResponse
import com.section11.movieknight.service.*
import kotlinx.coroutines.*

class CoroutineImdbMoviesInteractor(
    private val comingSoonMoviesService: ComingSoonMoviesSuspendService,
    private val inTheatersMoviesService: InTheatersMoviesSuspendService
) : MovieKnightInteractor {
    private lateinit var inTheatersMoviesJob: Job
    private lateinit var comingSoonMoviesJob: Job

    override fun getComingSoonMovies(callback: ComingSoonMoviesServiceCallback) {
        comingSoonMoviesJob = GlobalScope.launch(Dispatchers.Main) {
            val comingSoonResponse = withContext(Dispatchers.IO) {
                getComingSoonMovies()
            }

            callback.handleComingSoonResult(comingSoonResponse)
        }
    }

    override fun getInTheatersMovies(callback: InTheatersMoviesServiceCallback) {
        inTheatersMoviesJob = GlobalScope.launch(Dispatchers.Main) {
            val inTheatersResponse = withContext(Dispatchers.IO) {getInTheatersMovies()}

            callback.handleInTheatersResult(inTheatersResponse)
        }
    }

    override fun cancelPendingRequests() {
        if (this::inTheatersMoviesJob.isInitialized) {
            inTheatersMoviesJob.cancel()
        }

        if (this::comingSoonMoviesJob.isInitialized) {
            comingSoonMoviesJob.cancel()
        }
    }

    private suspend fun getInTheatersMovies(): InTheatersResponse =
        try {
            inTheatersMoviesService.getInTheatersMoviesCoroutine()
        } catch (e: Exception) {
            InTheatersResponse.EMPTY
        }

    private suspend fun getComingSoonMovies(): ComingSoonResponse =
        try {
            comingSoonMoviesService.getComingSoonMovies()
        } catch (e: Exception) {
            ComingSoonResponse.EMPTY
        }
}