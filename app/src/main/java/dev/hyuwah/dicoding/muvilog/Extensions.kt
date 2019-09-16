package dev.hyuwah.dicoding.muvilog

import android.app.Activity
import java.util.*

fun Activity.setLocale(locale: Locale) {
    Locale.setDefault(locale)
    val config = resources.configuration.apply { setLocale(locale) }
    config.locale = locale
    resources.updateConfiguration(config, resources.displayMetrics)
}