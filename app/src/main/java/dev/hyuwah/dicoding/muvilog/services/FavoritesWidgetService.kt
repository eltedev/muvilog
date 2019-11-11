package dev.hyuwah.dicoding.muvilog.services

import android.content.Intent
import android.widget.RemoteViewsService
import dagger.android.AndroidInjection
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.presentation.widget.FavoritesRemoteViewFactory
import javax.inject.Inject

class FavoritesWidgetService : RemoteViewsService() {

    @Inject
    lateinit var repository: Repository

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return FavoritesRemoteViewFactory(this.applicationContext, repository)
    }
}