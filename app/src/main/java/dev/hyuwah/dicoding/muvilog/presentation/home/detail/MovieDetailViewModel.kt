package dev.hyuwah.dicoding.muvilog.presentation.home.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.local.AppDatabase
import dev.hyuwah.dicoding.muvilog.data.remote.TheMovieDbServicesFactory
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import dev.hyuwah.dicoding.muvilog.presentation.model.toFavoriteMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = Repository(
        TheMovieDbServicesFactory.create(),
        AppDatabase.getInstance(app).favoriteMovieDao()
    )

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