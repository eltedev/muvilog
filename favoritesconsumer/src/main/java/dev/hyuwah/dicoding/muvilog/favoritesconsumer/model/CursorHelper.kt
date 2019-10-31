package dev.hyuwah.dicoding.muvilog.favoritesconsumer.model

import android.database.Cursor

object CursorHelper {

    fun convertToListFavorite(cursor: Cursor): List<FavoriteMovie> {
        val favorites = mutableListOf<FavoriteMovie>()
        while(cursor.moveToNext()){
            val movie = FavoriteMovie(
                movieId = cursor.getInt(cursor.getColumnIndexOrThrow("movie_id")),
                title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                posterUrl = cursor.getString(cursor.getColumnIndexOrThrow("poster_url")),
                backdropUrl = cursor.getString(cursor.getColumnIndexOrThrow("backdrop_url")),
                releaseDate =  cursor.getString(cursor.getColumnIndexOrThrow("release_date")),
                overview =  cursor.getString(cursor.getColumnIndexOrThrow("overview")),
                voteAverage =  cursor.getDouble(cursor.getColumnIndexOrThrow("vote_average")),
                voteCount = cursor.getInt(cursor.getColumnIndexOrThrow("vote_count")),
                category = cursor.getString(cursor.getColumnIndexOrThrow("category"))
            ).apply {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            }
            favorites.add(movie)
        }
        return favorites
    }
}