package dev.hyuwah.dicoding.muvilog.presentation.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var id: String,
    var title: String,
    var overview: String,
    @DrawableRes var poster: Int,
    var genre: String = "",
    var runtime: Int = 0,
    var rating: String = ""
) : Parcelable