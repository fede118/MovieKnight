package com.section11.movieknight.core

import com.section11.movieknight.service.ComingSoonMoviesService
import com.section11.movieknight.service.InTheatersMoviesService

object MovieKnightModule {

    private val dataModule = DataModule()

    fun getComingSoonMoviesRepository() : ComingSoonMoviesService {
        return dataModule.createRepositoryWithTimeOut(ComingSoonMoviesService::class.java)
    }

    fun getInTheatersRepository() : InTheatersMoviesService {
        return dataModule.createRepositoryWithTimeOut(InTheatersMoviesService::class.java)
    }
}