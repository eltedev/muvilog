package dev.hyuwah.dicoding.muvilog.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.local.SharedPrefSource
import dev.hyuwah.dicoding.muvilog.data.remote.TheMovieDbServicesFactory
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource

class TvShowListViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = Repository(TheMovieDbServicesFactory.create())
    private var lang = SharedPrefSource(app).getCurrentLangId()

    val discoverTvShow = liveData {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(repository.fetchDiscoverTvShow(lang)))
        }catch (t: Throwable){
            emit(Resource.Failure(t))
        }
    }
}