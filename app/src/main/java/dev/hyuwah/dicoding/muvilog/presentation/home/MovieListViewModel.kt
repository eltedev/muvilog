package dev.hyuwah.dicoding.muvilog.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.remote.TheMovieDbServicesFactory

class MovieListViewModel : ViewModel() {
    private val repository = Repository(TheMovieDbServicesFactory.create())

    val discoverMovies = liveData {
        emit(repository.fetchDiscoverMovies())
    }
}