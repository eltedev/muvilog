package dev.hyuwah.dicoding.muvilog.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie

@Dao
interface FavoriteMovieDao {

    @Insert
    suspend fun insert(movie: FavoriteMovie)

    @Query("SELECT * FROM favorite_movie WHERE category = \"movie\"")
    fun getFavoriteMovies(): LiveData<List<FavoriteMovie>>

    @Query("SELECT * FROM favorite_movie WHERE category = \"tvshow\"")
    fun getFavoriteTvShow(): LiveData<List<FavoriteMovie>>

    @Query("SELECT * FROM favorite_movie")
    fun getAllFavorite(): List<FavoriteMovie>

    @Query("SELECT * FROM favorite_movie WHERE movie_id = :movieId")
    fun getFavoriteMovie(movieId: Int): FavoriteMovie?

    @Query("DELETE FROM favorite_movie WHERE movie_id = :movieId")
    suspend fun delete(movieId: Int)

}