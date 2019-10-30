package dev.hyuwah.dicoding.muvilog

import android.app.Activity
import android.view.View
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
