package dev.hyuwah.dicoding.muvilog.data.local

import android.content.Context
import org.jetbrains.anko.defaultSharedPreferences
import javax.inject.Inject

class SharedPrefSource @Inject constructor(context: Context) {
    private val sp = context.defaultSharedPreferences

    fun getCurrentLangId() : String {
        return sp.getString("setting_lang","en") ?: "en"
    }
}