package com.section11.movieknight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.section11.components.recycler.model.ViewHolderModel
import com.section11.movieknight.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * MovieKnight Main Activity. Consists of 2 custom carrousels (recycler view), one shows coming soon
 * movies, the other in theaters
 */
@AndroidEntryPoint
class MovieKnightActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(applicationContext))
        binding.root.setOnRefreshListener(viewModel)
        setContentView(binding.root)

        viewModel.getMovies()
        binding.nowPlayingRecycler.setOnRecyclerItemClickedListener(viewModel)
        binding.comingSoonRecycler.setOnRecyclerItemClickedListener(viewModel)

        viewModel.state.observe(this, ::handleMoviesFetched)
        viewModel.event.observe(this, ::handleEvent)
    }

    private fun setComingSoonMoviesData(movieList: List<ViewHolderModel>) {
        binding.comingSoonRecycler.bind(movieList)
    }

    private fun setInTheatersMoviesData(movieList: List<ViewHolderModel>) {
        binding.nowPlayingRecycler.bind(movieList)
    }

    private fun handleMoviesFetched(state: MovieListViewModel.ViewState) {
        when (state) {
            is MovieListViewModel.ViewState.EmptyList -> {
                binding.comingSoonRecycler.placeHolderItems = state.placeHolderItems
                binding.nowPlayingRecycler.placeHolderItems = state.placeHolderItems
            }

            is MovieListViewModel.ViewState.ComingSoonMovies -> {
                setRefreshProgressBar(false)
                setComingSoonMoviesData(state.comingSoonMovies)
            }

            is MovieListViewModel.ViewState.InTheatersMovies -> {
                setRefreshProgressBar(false)
                setInTheatersMoviesData(state.inTheatersMovies)
            }
        }
    }

    @SuppressWarnings("ForbiddenComment")
    private fun handleEvent(event: MovieListViewModel.Event) {
        when(event) {
            is MovieListViewModel.Event.ShowMovieDetails -> {
                // TODO: Navigate to detail activity
                Toast.makeText(this, "Movie touched: " + event.movieTitle, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setRefreshProgressBar(show: Boolean) {
        binding.root.isRefreshing = show
    }
}
