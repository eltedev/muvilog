package dev.hyuwah.dicoding.muvilog.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.local.SharedPrefSource
import dev.hyuwah.dicoding.muvilog.data.remote.TheMovieDbServicesFactory
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource

class MovieListViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = Repository(TheMovieDbServicesFactory.create())
    private var lang = SharedPrefSource(app).getCurrentLangId()

    val discoverMovies = liveData {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(repository.fetchDiscoverMovies(lang)))
        } catch (t: Throwable) {
            emit(Resource.Failure(t))
        }
    }
}