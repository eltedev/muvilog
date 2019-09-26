package dev.hyuwah.dicoding.muvilog.data

import dev.hyuwah.dicoding.muvilog.data.remote.ITheMovieDbServices
import dev.hyuwah.dicoding.muvilog.data.remote.model.toPresentation
import dev.hyuwah.dicoding.muvilog.data.remote.toPresentation
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem

class Repository(
    private val tmdbServices: ITheMovieDbServices
): IRepository {

    override suspend fun fetchDiscoverMovies(lang: String) : List<MovieItem> {
        return tmdbServices.getDiscoverMovies(lang, 1, "ID").toPresentation()
    }

    override suspend fun fetchDiscoverTvShow(lang: String) : List<MovieItem> {
        return tmdbServices.getDiscoverTvShow(lang, 1).toPresentation()
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