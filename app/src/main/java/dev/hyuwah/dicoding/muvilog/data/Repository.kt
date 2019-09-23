package dev.hyuwah.dicoding.muvilog.data

import dev.hyuwah.dicoding.muvilog.data.remote.ITheMovieDbServices
import dev.hyuwah.dicoding.muvilog.data.remote.model.toPresentation
import dev.hyuwah.dicoding.muvilog.data.remote.toPresentation
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem

class Repository(private val tmdbServices: ITheMovieDbServices): IRepository {

    override suspend fun fetchDiscoverMovies() : List<MovieItem> {
        return tmdbServices.getDiscoverMovies("id", 1, "ID").toPresentation()
    }

    override suspend fun fetchDiscoverTvShow() : List<MovieItem> {
        return tmdbServices.getDiscoverTvShow("id", 1).toPresentation()
    }

    override suspend fun fetchPopularMovies()  {
        tmdbServices.getPopularMovies("id",1,"ID").toPresentation()
    }

    override suspend fun fetchNowPlayingMovies() {
        tmdbServices.getNowPlayingMovies("id",1,"ID").toPresentation()
    }

    override suspend fun fetchPopularTvShow() {

    }
}