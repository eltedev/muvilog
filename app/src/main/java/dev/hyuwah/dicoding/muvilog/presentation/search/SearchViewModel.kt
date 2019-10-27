package dev.hyuwah.dicoding.muvilog.presentation.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.hyuwah.dicoding.muvilog.data.Constants
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.local.AppDatabase
import dev.hyuwah.dicoding.muvilog.data.local.SharedPrefSource
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie
import dev.hyuwah.dicoding.muvilog.data.remote.TheMovieDbServicesFactory
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource
import dev.hyuwah.dicoding.muvilog.presentation.model.toFavoriteMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = Repository(
        TheMovieDbServicesFactory.create(),
        AppDatabase.getInstance(app).favoriteMovieDao()
    )
    private var lang = SharedPrefSource(app).getCurrentLangId()

    private val _state = MutableLiveData<Resource<List<FavoriteMovie>>>(Resource.Loading)
    val state: LiveData<Resource<List<FavoriteMovie>>> = _state

    fun search(query: String){
        _state.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val movieList = repository.searchMovie(query, lang).map { it.toFavoriteMovie(Constants.MOVIE_KEY) }
            val tvList = repository.searchTv(query, lang).map { it.toFavoriteMovie(Constants.TV_SHOW_KEY) }
            val result = movieList + tvList
            _state.postValue(Resource.Success(result.sortedWith(compareBy({-it.voteCount},{-it.voteAverage}))))
        }
    }
}