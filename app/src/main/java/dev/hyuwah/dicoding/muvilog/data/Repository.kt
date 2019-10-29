package dev.hyuwah.dicoding.muvilog.data

import dev.hyuwah.dicoding.muvilog.data.local.FavoriteMovieDao
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie
import dev.hyuwah.dicoding.muvilog.data.remote.ITheMovieDbServices
import dev.hyuwah.dicoding.muvilog.data.remote.model.toPresentation
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import java.text.SimpleDateFormat
import java.util.*

class Repository(
    private val tmdbServices: ITheMovieDbServices,
    private val favoriteMovieDao: FavoriteMovieDao
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

    override suspend fun searchMovie(query: String, lang: String): List<MovieItem> {
        return try {
            tmdbServices.searchMovie(lang, query).toPresentation()
        }catch (e: Throwable){
            listOf()
        }
    }

    override suspend fun searchTv(query: String, lang: String): List<MovieItem> {
        return try {
            tmdbServices.searchTv(lang, query).toPresentation()
        } catch (e: Throwable){
            listOf()
        }
    }

    override fun getFavoriteMovies() = favoriteMovieDao.getFavoriteMovies()
    override fun getFavoriteTvShow() = favoriteMovieDao.getFavoriteTvShow()
    override fun getAllFavorite(): List<FavoriteMovie> = favoriteMovieDao.getAllFavorite()
    override suspend fun getFavoriteMovie(movieId: Int) = favoriteMovieDao.getFavoriteMovie(movieId)
    override suspend fun removeFavorite(movieId: Int) = favoriteMovieDao.delete(movieId)
    override suspend fun addFavorite(favoriteMovie: FavoriteMovie) = favoriteMovieDao.insert(favoriteMovie)

    override suspend fun getTodayReleasedMovies(): List<MovieItem> {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        return try {
            tmdbServices.getTodayReleasedMovies(today, today).toPresentation()
        }catch (e: Throwable){
            listOf()
        }
    }
}