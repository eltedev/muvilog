package dev.hyuwah.dicoding.muvilog.favoritesconsumer

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

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