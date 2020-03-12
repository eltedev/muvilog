package dev.hyuwah.dicoding.muvilog.presentation.model

import android.os.Parcelable
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewItem(
    val author: String,
    val content: String,
    val id: String,
    val url: String
) : Parcelable