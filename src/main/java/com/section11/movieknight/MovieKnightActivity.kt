package com.section11.movieknight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.section11.movieknight.core.MovieKnightModule
import com.section11.movieknight.core.SchedulerProvider
import com.section11.movieknight.interactor.ImDbMoviesInteractor
import com.section11.movieknight.ui.MovieKnightPresenter
import com.section11.movieknight.ui.MovieKnightView

class MovieKnightActivity : AppCompatActivity(), MovieKnightView {

    lateinit var presenter: MovieKnightPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieKnightModule = MovieKnightModule(applicationContext)

        presenter = MovieKnightPresenter(
            this,
            ImDbMoviesInteractor(movieKnightModule.getComingSoonMoviesRepository()),
            SchedulerProvider()
        )
    }
}