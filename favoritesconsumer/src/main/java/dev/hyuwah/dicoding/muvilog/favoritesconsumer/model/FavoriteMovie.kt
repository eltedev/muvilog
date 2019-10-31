package dev.hyuwah.dicoding.muvilog.favoritesconsumer.model

import com.google.gson.annotations.SerializedName

data class FavoriteMovie(
    @SerializedName("movie_id")
    val movieId: Int,
    val title: String,
    @SerializedName("poster_url")
    val posterUrl: String,
    @SerializedName("backdrop_url")
    val backdropUrl: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val overview: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val category: String
){
    var id: Int = 0

//    fun toMovieItem(): MovieItem {
//        return MovieItem(movieId, title, posterUrl, backdropUrl, releaseDate, overview, voteAverage, voteCount)
//    }
}