package dev.hyuwah.dicoding.muvilog

import android.app.Application
import com.facebook.stetho.Stetho

class MuvilogApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}