package com.section11.movieknight.ui

import com.section11.components.recycler.model.ViewHolderModel

interface MovieKnightView {

    fun setComingSoonMoviesData(movieList: List<ViewHolderModel>)

    fun setNowPlayingMoviesData(movieList: List<ViewHolderModel>)
}
