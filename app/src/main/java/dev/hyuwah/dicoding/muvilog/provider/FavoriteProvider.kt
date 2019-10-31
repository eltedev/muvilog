package dev.hyuwah.dicoding.muvilog.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import dev.hyuwah.dicoding.muvilog.BuildConfig
import dev.hyuwah.dicoding.muvilog.data.local.AppDatabase

class FavoriteProvider : ContentProvider() {

    companion object{
        private const val AUTHORITY = "${BuildConfig.APPLICATION_ID}.provider"
        private const val BASE_PATH = "favorites"
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$BASE_PATH")
        private const val FAVORITE = 1
        private const val FAVORITE_ID = 2

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, BASE_PATH, FAVORITE)
            uriMatcher.addURI(AUTHORITY, "$BASE_PATH/#", FAVORITE_ID)
        }

    }

    private lateinit var db : AppDatabase

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        if (context == null){
            return null
        }
        val cursor: Cursor?
        val favoriteMovies = db.favoriteMovieDao()
        val code = uriMatcher.match(uri)
        if (code in FAVORITE..FAVORITE_ID){
            cursor = when(uriMatcher.match(uri)){
                FAVORITE -> favoriteMovies.getAllFavoriteCursor()
                FAVORITE_ID -> favoriteMovies.getFavoriteMovieCursor(ContentUris.parseId(uri).toInt())
                else -> {
                    null
                }
            }
            cursor?.setNotificationUri(context?.contentResolver, uri)
            return cursor
        }else throw IllegalArgumentException("Unknown Uri")
    }



    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreate(): Boolean {
        db = AppDatabase.getInstance(context)
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return -1
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return -1
    }

    override fun getType(uri: Uri): String? {
        return when(uriMatcher.match(uri)){
            FAVORITE -> "vnd.android.cursor.dir/$AUTHORITY.$BASE_PATH"
            FAVORITE_ID -> "vnd.android.cursor.item/$AUTHORITY.$BASE_PATH"
            else -> throw IllegalArgumentException("Unknown Uri $uri")
        }
    }
}