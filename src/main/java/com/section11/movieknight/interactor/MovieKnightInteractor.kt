package com.section11.movieknight.interactor

import com.section11.movieknight.dto.Movie
import io.reactivex.Observable

interface MovieKnightInteractor {

    fun getComingSoonMovies() : Observable<List<Movie>>
}