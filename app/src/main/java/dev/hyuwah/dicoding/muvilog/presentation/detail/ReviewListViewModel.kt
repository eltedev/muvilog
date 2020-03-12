package dev.hyuwah.dicoding.muvilog.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.presentation.model.ReviewItem
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReviewListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _state = MutableLiveData<Resource<List<ReviewItem>>>()
    val state: LiveData<Resource<List<ReviewItem>>> = _state

    fun load(id:String) {
        _state.postValue(Resource.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {

                _state.postValue(Resource.Success(repository.fetchMovieReview(id)))


            } catch (t: Throwable) {
                _state.postValue(Resource.Failure(t))
            }
        }
    }

}