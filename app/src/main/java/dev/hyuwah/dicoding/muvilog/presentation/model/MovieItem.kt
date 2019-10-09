package dev.hyuwah.dicoding.muvilog.presentation.model

import android.os.Parcelable
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val backdropUrl: String,
    val releaseDate: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Int
) : Parcelable

fun MovieItem.toFavoriteMovie(category: String): FavoriteMovie {
    return FavoriteMovie(
        movieId = id,
        title = title,
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        releaseDate = releaseDate,
        overview = overview,
        voteAverage = voteAverage,
        voteCount = voteCount,
        category = category
    )
}