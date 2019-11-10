package dev.hyuwah.dicoding.muvilog.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.dicoding.muvilog.data.Constants
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.local.SharedPrefSource
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource
import dev.hyuwah.dicoding.muvilog.presentation.model.toFavoriteMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: Repository,
    sharedPrefSource: SharedPrefSource
    ) : ViewModel() {

    private var lang = sharedPrefSource.getCurrentLangId()

    private val _state = MutableLiveData<Resource<List<FavoriteMovie>>>()
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