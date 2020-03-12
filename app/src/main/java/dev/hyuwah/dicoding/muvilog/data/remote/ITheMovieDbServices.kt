package dev.hyuwah.dicoding.muvilog.data.remote

import dev.hyuwah.dicoding.muvilog.data.remote.model.DiscoverMoviesResponse
import dev.hyuwah.dicoding.muvilog.data.remote.model.DiscoverTvResponse
import dev.hyuwah.dicoding.muvilog.data.remote.model.MoviesResponse
import dev.hyuwah.dicoding.muvilog.data.remote.model.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): MoviesResponse

    @GET("tv/popular")
    suspend fun getPopularTvShow(
        @Query("language") lang: String,
        @Query("page") page: Int
    )

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("language") lang: String,
        @Query("query") query: String
    ): DiscoverMoviesResponse

    @GET("search/tv")
    suspend fun searchTv(
        @Query("language") lang: String,
        @Query("query") query: String
    ): DiscoverTvResponse

    @GET("discover/movie")
    suspend fun getTodayReleasedMovies(
        @Query("primary_release_date.gte") releaseDateGte: String,
        @Query("primary_release_date.lte") releaseDateLte: String
    ): DiscoverMoviesResponse

    @GET("movie/{id}/reviews")
    suspend fun getMovieReview(
        @Path("id") id:String
    ): ReviewResponse

}