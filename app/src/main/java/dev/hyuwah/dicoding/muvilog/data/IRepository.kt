package dev.hyuwah.dicoding.muvilog.data

import androidx.lifecycle.LiveData
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem

interface IRepository {

    suspend fun fetchDiscoverMovies(lang: String): List<MovieItem>
    suspend fun fetchDiscoverTvShow(lang: String): List<MovieItem>

     suspend fun fetchPopularMovies()
     suspend fun fetchNowPlayingMovies()

     suspend fun fetchPopularTvShow()

    fun getFavoriteMovies(): LiveData<List<FavoriteMovie>>
    fun getFavoriteTvShow(): LiveData<List<FavoriteMovie>>
    fun getAllFavorite(): List<FavoriteMovie>
    suspend fun getFavoriteMovie(movieId: Int): FavoriteMovie?
    suspend fun removeFavorite(movieId: Int)
    suspend fun addFavorite(favoriteMovie: FavoriteMovie)

    suspend fun searchMovie(query: String, lang: String): List<MovieItem>
    suspend fun searchTv(query: String, lang: String): List<MovieItem>

    suspend fun getTodayReleasedMovies(): List<MovieItem>
}