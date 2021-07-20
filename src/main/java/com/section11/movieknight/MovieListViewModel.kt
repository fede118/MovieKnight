package com.section11.movieknight

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.section11.components.recycler.listener.RecyclerItemClickListener
import com.section11.components.recycler.model.ViewHolderModel
import com.section11.movieknight.dto.Movie
import com.section11.movieknight.service.FetchComingSoonMovies
import com.section11.movieknight.service.FetchInTheatersMovies

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val fetchComingSoonMovies: FetchComingSoonMovies,
    private val fetchInTheatersMovies: FetchInTheatersMovies
) :ViewModel(), RecyclerItemClickListener {

    private val _state = MutableLiveData<ViewState>()
    val state: LiveData<ViewState> = _state

    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event> = _event

    fun getMovies() {
        _state.value = ViewState.EmptyList(DEFAULT_PLACE_HOLDER_ITEMS)
        viewModelScope.launch {
            val comingSoonMovies = fetchComingSoonMovies()

            _state.value = ViewState.ComingSoonMovies(comingSoonMovies)
        }

        viewModelScope.launch {
            val inTheatersMovies = fetchInTheatersMovies()

            _state.value = ViewState.InTheatersMovies(inTheatersMovies)
        }
    }

    override fun onRecyclerItemClicked(movie: ViewHolderModel) {
        movie.getTitle()?.let {
            _event.value = Event.ShowMovieDetails(it)
        }
    }

    sealed class ViewState {
        data class InTheatersMovies(val inTheatersMovies: List<Movie>): ViewState()

        data class ComingSoonMovies(val comingSoonMovies: List<Movie>): ViewState()

        data class EmptyList(val placeHolderItems: Int): ViewState()
    }

    sealed class Event {
        data class ShowMovieDetails(val movieTitle: String): Event()
    }

    companion object {
        private const val DEFAULT_PLACE_HOLDER_ITEMS = 10
    }
}
