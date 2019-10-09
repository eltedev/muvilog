package dev.hyuwah.dicoding.muvilog

import android.app.Activity
import android.view.View
import dev.hyuwah.dicoding.muvilog.data.DataHelper
import java.text.SimpleDateFormat
import java.util.*

fun Activity.setLocale(locale: Locale) {
    Locale.setDefault(locale)
    val config = resources.configuration.apply { setLocale(locale) }
    config.locale = locale
    resources.updateConfiguration(config, resources.displayMetrics)
}

fun String.asImageUrl() = DataHelper.IMG_BASE_URL + "w185" + this

fun String.toNormalDateFormat(): String {
    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
    return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}
