package com.section11.movieknight.core

import android.content.Context
import com.section11.movieknight.service.ComingSoonMoviesService

class MovieKnightModule(context: Context) {

    private val dataModule = DataModule()

    fun getComingSoonMoviesRepository() : ComingSoonMoviesService {
        return dataModule.createRepositoryWithTimeOut(ComingSoonMoviesService::class.java)
    }

    fun getNowInTheatersRepository() {
        //todo
    }
}