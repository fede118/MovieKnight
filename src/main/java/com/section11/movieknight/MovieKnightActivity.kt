package com.section11.movieknight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.section11.components.recycler.listener.RecyclerItemClickListener
import com.section11.components.recycler.model.ViewHolderModel
import com.section11.movieknight.core.MovieKnightModule
import com.section11.movieknight.core.SchedulerProvider
import com.section11.movieknight.databinding.ActivityMainBinding
import com.section11.movieknight.interactor.ImDbMoviesInteractor
import com.section11.movieknight.ui.MovieKnightPresenter
import com.section11.movieknight.ui.MovieKnightView

class MovieKnightActivity : AppCompatActivity(), MovieKnightView, RecyclerItemClickListener {

    private lateinit var presenter: MovieKnightPresenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

        presenter = MovieKnightPresenter(
            this,
            ImDbMoviesInteractor(
                MovieKnightModule.getComingSoonMoviesRepository(),
                MovieKnightModule.getInTheatersRepository()
            ),
            SchedulerProvider()
        )
    }

    override fun setComingSoonMoviesData(movieList: List<ViewHolderModel>) {
        binding.comingSoonRecycler.bind(movieList, this)
    }

    override fun setNowPlayingMoviesData(movieList: List<ViewHolderModel>) {
        binding.nowPlayingRecycler.bind(movieList, this)
    }

    override fun onRecyclerItemClicked(model: ViewHolderModel) {
        Toast.makeText(this, "Movie touched: " + model.getTitle(), Toast.LENGTH_SHORT).show()
    }
}