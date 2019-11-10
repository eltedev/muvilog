package dev.hyuwah.dicoding.muvilog.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableLiveData<Resource<List<FavoriteMovie>>>(Resource.Loading)

    val state: LiveData<Resource<List<FavoriteMovie>>> = _state

    fun load(){
        _state.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getAllFavorite()
            _state.postValue(Resource.Success(list))
        }
    }

}
