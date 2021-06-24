package com.section11.movieknight.core

import com.section11.movieknight.service.ComingSoonMoviesService
import com.section11.movieknight.service.ComingSoonMoviesSuspendService
import com.section11.movieknight.service.InTheatersMoviesService
import com.section11.movieknight.service.InTheatersMoviesSuspendService

object MovieKnightModule {

    private val dataModule = DataModule()

    fun getComingSoonMoviesRepository() : ComingSoonMoviesService {
        return dataModule.createRepositoryWithDefaultTimeOut(ComingSoonMoviesService::class.java)
    }

    fun getComingSoonMoviesSuspendRepository() : ComingSoonMoviesSuspendService {
        return dataModule.createRepositoryWithDefaultTimeOut(ComingSoonMoviesSuspendService::class.java)
    }

    fun getInTheatersRepository() : InTheatersMoviesService {
        return dataModule.createRepositoryWithDefaultTimeOut(InTheatersMoviesService::class.java)
    }

    fun getInTheatersSuspendRepository() : InTheatersMoviesSuspendService {
        return dataModule.createRepositoryWithDefaultTimeOut(InTheatersMoviesSuspendService::class.java)
    }
}