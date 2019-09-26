package dev.hyuwah.dicoding.muvilog.data

import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem

interface IRepository {

    suspend fun fetchDiscoverMovies(lang: String): List<MovieItem>
    suspend fun fetchDiscoverTvShow(lang: String): List<MovieItem>

     suspend fun fetchPopularMovies()
     suspend fun fetchNowPlayingMovies()

     suspend fun fetchPopularTvShow()

}