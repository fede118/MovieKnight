package com.section11.movieknight.interactor

import android.annotation.SuppressLint
import android.util.Log
import com.section11.movieknight.core.di.SchedulerProvider
import com.section11.movieknight.dto.ComingSoonResponse
import com.section11.movieknight.dto.InTheatersResponse
import com.section11.movieknight.dto.Movie
import com.section11.movieknight.service.*
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class RxJavaImDbMoviesInteractor(
    private val comingSoonMoviesService: ComingSoonMoviesService,
    private val inTheatersMoviesService: InTheatersMoviesService,
    private val schedulerProvider: SchedulerProvider
) : MovieKnightInteractor {

    private lateinit var inTheatersListener: InTheatersMoviesServiceCallback
    private lateinit var comingSoonListener: ComingSoonMoviesServiceCallback
    private var comingSoonMoviesDisposable: Disposable? = null
    private var inTheatersMoviesDisposable: Disposable? = null

    @SuppressLint("CheckResult")
    override fun getComingSoonMovies(callback: ComingSoonMoviesServiceCallback) {
        this.comingSoonListener = callback
        getComingSoonMovies()
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .doOnSubscribe {
                comingSoonMoviesDisposable = it
            }
            .subscribe(this::onComingSoonMoviesReceived, this::onComingSoonMoviesFailure)

    }

    @SuppressLint("CheckResult")
    override fun getInTheatersMovies(callback: InTheatersMoviesServiceCallback) {
        this.inTheatersListener = callback
        getInTheatersMovies()
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .doOnSubscribe {
                inTheatersMoviesDisposable = it
            }
            .subscribe(this::onNowPlayingMoviesReceived, this::onInTheatersMoviesFailure)
    }

    override fun cancelPendingRequests() {
        comingSoonMoviesDisposable?.isDisposed ?: comingSoonMoviesDisposable!!.dispose()
        inTheatersMoviesDisposable?.isDisposed ?: inTheatersMoviesDisposable!!.dispose()
    }

    private fun getInTheatersMovies(): Observable<List<Movie>> {
        return inTheatersMoviesService.getInTheatersMovies()
            .map {
                it.movies
            }
    }

    private fun getComingSoonMovies(): Observable<List<Movie>> {
        return comingSoonMoviesService.getComingSoonMovies()
            .map {
                it.movies
            }
    }

    private fun onNowPlayingMoviesReceived(inTheatersMovies: List<Movie>) {
        inTheatersListener.handleInTheatersResult(InTheatersResponse(inTheatersMovies))
    }

    private fun onInTheatersMoviesFailure(throwable: Throwable) {
        Log.e("Error fetching in theaters movies with RX Java interactor",
            throwable.message ?: "unable to get exception message")
        inTheatersListener.handleInTheatersResult(InTheatersResponse.EMPTY)
    }

    private fun onComingSoonMoviesReceived(comingSoonMovies: List<Movie>) {
        comingSoonListener.handleComingSoonResult(ComingSoonResponse(comingSoonMovies))
    }

    private fun onComingSoonMoviesFailure(throwable: Throwable) {
        Log.e("Error fetching coming soon movies with RX Java interactor",
            throwable.message ?: "unable to get exception message")
        comingSoonListener.handleComingSoonResult(ComingSoonResponse.EMPTY)
    }

}