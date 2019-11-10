package dev.hyuwah.dicoding.muvilog.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import dev.hyuwah.dicoding.muvilog.presentation.model.toFavoriteMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private lateinit var movies: MovieItem
    private lateinit var type: String

    private var _isFavorite = MutableLiveData(false)

    fun setMovieAndType(movieItem: MovieItem, type: String){
        movies = movieItem
        this.type = type
        viewModelScope.launch(Dispatchers.IO) {
            _isFavorite.postValue(repository.getFavoriteMovie(movies.id) != null)
        }
    }

    fun isFavorite(): LiveData<Boolean> = _isFavorite

    fun saveToFavorite() {
        viewModelScope.launch {
            repository.addFavorite(movies.toFavoriteMovie(type))
            _isFavorite.value = true
        }
    }

    fun removeFromFavorite() {
        viewModelScope.launch {
            repository.removeFavorite(movies.id)
            _isFavorite.value = false
        }
    }
}