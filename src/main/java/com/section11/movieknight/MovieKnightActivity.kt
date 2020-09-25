package com.section11.movieknight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.section11.movieknight.core.MovieKnightModule
import com.section11.movieknight.core.SchedulerProvider
import com.section11.movieknight.interactor.MovieKnightInteractor
import com.section11.movieknight.ui.MovieKnightPresenter

class MovieKnightActivity : AppCompatActivity() {

    lateinit var presenter: MovieKnightPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieKnightModule = MovieKnightModule(applicationContext)

        presenter = MovieKnightPresenter(
            MovieKnightInteractor(movieKnightModule.getComingSoonMoviesRepository()),
            SchedulerProvider()
        )
    }
}