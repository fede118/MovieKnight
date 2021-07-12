package com.section11.movieknight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.section11.components.recycler.listener.RecyclerItemClickListener
import com.section11.components.recycler.model.ViewHolderModel
import com.section11.movieknight.core.InteractorsProvider
import com.section11.movieknight.databinding.ActivityMainBinding
import com.section11.movieknight.ui.MovieKnightPresenter
import com.section11.movieknight.ui.MovieKnightView

class MovieKnightActivity : AppCompatActivity(), MovieKnightView, RecyclerItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var presenter: MovieKnightPresenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(applicationContext))
        binding.root.setOnRefreshListener(this)
        setContentView(binding.root)

        // todo: hard coded true, should be a feature flag
        presenter = MovieKnightPresenter(
            this,
            InteractorsProvider.getInteractor(this, true)
        )
    }

    override fun onStop() {
        super.onStop()
        presenter.onStopReached()
    }

    override fun setComingSoonMoviesData(movieList: List<ViewHolderModel>) {
        binding.comingSoonRecycler.bind(movieList, this)
    }

    override fun setNowPlayingMoviesData(movieList: List<ViewHolderModel>) {
        binding.nowPlayingRecycler.bind(movieList, this)
    }

    override fun onRecyclerItemClicked(model: ViewHolderModel) {
        // todo: show movie activity or detail activity
        Toast.makeText(this, "Movie touched: " + model.getTitle(), Toast.LENGTH_SHORT).show()
    }

    override fun showErrorToast(errorMessage: String) {
        // todo: show error screen
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun hideRefreshProgressBar() {
        binding.root.isRefreshing = false
    }

    override fun showRefreshProgressBar() {
        binding.root.isRefreshing = true
    }

    override fun onRefresh() {
        presenter.onPulledToRefresh()
    }
}
