package dev.hyuwah.dicoding.muvilog.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.local.SharedPrefSource
import dev.hyuwah.dicoding.muvilog.data.remote.TheMovieDbServicesFactory

class MovieListViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = Repository(TheMovieDbServicesFactory.create())

    val discoverMovies = liveData {
        emit(repository.fetchDiscoverMovies(SharedPrefSource(app).getCurrentLangId()))
    }
}