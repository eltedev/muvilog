package dev.hyuwah.dicoding.muvilog.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem

@Entity(tableName = "favorite_movie")
data class FavoriteMovie(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    val title: String,
    @ColumnInfo(name = "poster_url")
    val posterUrl: String,
    @ColumnInfo(name = "backdrop_url")
    val backdropUrl: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    val overview: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
    val category: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun toMovieItem(): MovieItem {
        return MovieItem(movieId, title, posterUrl, backdropUrl, releaseDate, overview, voteAverage, voteCount)
    }
}