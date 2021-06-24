package com.section11.movieknight.core

import com.section11.movieknight.interactor.CoroutineImdbMoviesInteractor
import com.section11.movieknight.interactor.MovieKnightInteractor
import com.section11.movieknight.interactor.RxJavaImDbMoviesInteractor

object MoviesInteractorProvider {

    fun getMoviesInteractors(
        moviesInteractorWithCoroutineFeatureFlag: Boolean
    ): MovieKnightInteractor {
        return if (moviesInteractorWithCoroutineFeatureFlag) {
            CoroutineImdbMoviesInteractor(
                MovieKnightModule.getComingSoonMoviesSuspendRepository(),
                MovieKnightModule.getInTheatersSuspendRepository()
            )
        } else {
            RxJavaImDbMoviesInteractor(
                MovieKnightModule.getComingSoonMoviesRepository(),
                MovieKnightModule.getInTheatersRepository(),
                SchedulerProvider()
            )
        }
    }
}