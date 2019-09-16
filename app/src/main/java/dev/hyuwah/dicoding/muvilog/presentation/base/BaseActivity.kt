package dev.hyuwah.dicoding.muvilog.presentation.base

import android.annotation.SuppressLint
import android.content.Context
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import dev.hyuwah.dicoding.muvilog.setLocale
import java.util.*

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val locale = Locale(prefs.getString("setting_lang","en"))
        this.setLocale(locale)
    }

}