package dev.hyuwah.dicoding.muvilog.data.remote

import dev.hyuwah.dicoding.muvilog.data.remote.model.DiscoverMoviesResponse
import dev.hyuwah.dicoding.muvilog.data.remote.model.DiscoverTvResponse
import dev.hyuwah.dicoding.muvilog.data.remote.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ITheMovieDbServices {

    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): DiscoverMoviesResponse

    @GET("discover/tv")
    suspend fun getDiscoverTvShow(
        @Query("language") lang: String,
        @Query("page") page: Int
    ): DiscoverTvResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): MoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): MoviesResponse

    @GET("tv/popular")
    suspend fun getPopularTvShow(
        @Query("language") lang: String,
        @Query("page") page: Int
    )
}