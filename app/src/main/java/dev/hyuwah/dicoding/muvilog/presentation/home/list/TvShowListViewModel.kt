package dev.hyuwah.dicoding.muvilog.presentation.home.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.local.SharedPrefSource
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TvShowListViewModel @Inject constructor(
    private val repository: Repository,
    sharedPrefSource: SharedPrefSource
) : ViewModel() {

    private var lang = sharedPrefSource.getCurrentLangId()

    private var _state = MutableLiveData<Resource<List<MovieItem>>>()
    val state: LiveData<Resource<List<MovieItem>>> = _state

    fun load(){
        _state.postValue(Resource.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(Resource.Success(repository.fetchDiscoverTvShow(lang)))
            } catch (t: Throwable) {
                _state.postValue(Resource.Failure(t))
            }
        }
    }
}