package dev.hyuwah.dicoding.muvilog

import android.app.Activity
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dev.hyuwah.dicoding.muvilog.data.Constants
import java.text.SimpleDateFormat
import java.util.*

fun Activity.setLocale(locale: Locale) {
    Locale.setDefault(locale)
    val config = resources.configuration.apply { setLocale(locale) }
    config.locale = locale
    resources.updateConfiguration(config, resources.displayMetrics)
}

fun String.asPosterUrl() = if (this.isEmpty()) Constants.DEFAULT_POSTER_URL else Constants.IMG_BASE_URL + "w185" + this
fun String.asBackdropUrl() = if (this.isEmpty()) Constants.DEFAULT_BACKDROP_URL else Constants.IMG_BASE_URL + "w780" + this

fun String.toNormalDateFormat(): String {
    if (this.isEmpty() || this.isBlank()) return ""
    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
    return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun ImageView.load(url: String, @DrawableRes placeholder: Int) {
    Glide.with(this).load(url).placeholder(placeholder)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.load(gradientDrawable: GradientDrawable){
    Glide.with(this).load(gradientDrawable)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}
