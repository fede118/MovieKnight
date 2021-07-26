package com.section11.movieknight

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.section11.components.recycler.listener.RecyclerItemClickListener
import com.section11.components.recycler.model.ViewHolderModel
import com.section11.movieknight.core.Constants.DEFAULT_PLACE_HOLDER_ITEMS
import com.section11.movieknight.core.Constants.MOVIES_VALIDITY_TIME_AMOUNT
import com.section11.movieknight.core.Constants.MOVIES_VALIDITY_TIME_MEASURE
import com.section11.movieknight.db.MoviesLocalRepository
import com.section11.movieknight.dto.Movie
import com.section11.movieknight.dto.MovieType
import com.section11.movieknight.service.FetchComingSoonMovies
import com.section11.movieknight.service.FetchInTheatersMovies

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val fetchComingSoonMovies: FetchComingSoonMovies,
    private val fetchInTheatersMovies: FetchInTheatersMovies,
    private val moviesLocalRepository: MoviesLocalRepository
) :ViewModel(), RecyclerItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private val _state = MutableLiveData<ViewState>()
    val state: LiveData<ViewState> = _state

    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event> = _event

    /**
     * Gets ComingSoon and InTheaters Movies from repository if already fetch or from IMDB API
     * in case DB is empty
     */
    fun getMovies() {
        _state.value = ViewState.EmptyList(DEFAULT_PLACE_HOLDER_ITEMS)
        if (shouldFetchMoviesFromRemote()) {
            getRemoteMovies()
        } else {
            getCachedMovies()
        }
    }

    /**
     * Forces the call to IMDB API and overwrites what is saved in DB
     */
    fun forceMovieUpdate() {
        _state.value = ViewState.EmptyList(DEFAULT_PLACE_HOLDER_ITEMS)
        launchApiCall(MovieType.COMING_SOON)
        launchApiCall(MovieType.IN_THEATERS)
    }


    override fun onRefresh() {
        forceMovieUpdate()
    }

    override fun onRecyclerItemClicked(movie: ViewHolderModel) {
        movie.getTitle()?.let {
            _event.value = Event.ShowMovieDetails(it)
        }
    }

    private fun launchApiCall(type: MovieType) {
        viewModelScope.launch {
            when (type) {
                MovieType.COMING_SOON -> mapAndSaveMovies(fetchComingSoonMovies(), MovieType.COMING_SOON)
                MovieType.IN_THEATERS -> mapAndSaveMovies(fetchInTheatersMovies(), MovieType.IN_THEATERS)
            }
        }
    }

    private fun getRemoteMovies() {
        launchApiCall(MovieType.COMING_SOON)
        launchApiCall(MovieType.IN_THEATERS)
    }

    private fun getCachedMovies() =
        viewModelScope.launch {
            _state.value = ViewState.ComingSoonMovies(moviesLocalRepository.getComingSoonMovies())
            _state.value = ViewState.InTheatersMovies(moviesLocalRepository.getInTheatersMovies())
        }

    private suspend fun mapAndSaveMovies(movies: List<Movie>, type: MovieType) {
        mapToMovieType(type, movies)

        when (type) {
            MovieType.COMING_SOON -> _state.value = ViewState.ComingSoonMovies(movies)
            MovieType.IN_THEATERS -> _state.value = ViewState.InTheatersMovies(movies)
        }

        moviesLocalRepository.replaceMoviesWithType(movies, type)
    }

    private fun mapToMovieType(movieType: MovieType, movies: List<Movie>) {
        movies.map {
            it.movieType = movieType
        }
    }

    private fun shouldFetchMoviesFromRemote(): Boolean {
        val savedDate = moviesLocalRepository.getMoviesSavedDate()
        savedDate?.let {
            val calendar = Calendar.getInstance()
            calendar.time = it
            calendar.add(MOVIES_VALIDITY_TIME_MEASURE, MOVIES_VALIDITY_TIME_AMOUNT)
            return Date().after(calendar.time)
        } ?: return true
    }

    sealed class ViewState {
        data class InTheatersMovies(val inTheatersMovies: List<Movie>): ViewState()

        data class ComingSoonMovies(val comingSoonMovies: List<Movie>): ViewState()

        data class EmptyList(val placeHolderItems: Int): ViewState()
    }

    sealed class Event {
        data class ShowMovieDetails(val movieTitle: String): Event()
    }
}
