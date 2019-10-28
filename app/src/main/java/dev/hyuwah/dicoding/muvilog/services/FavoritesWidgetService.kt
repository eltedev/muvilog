package dev.hyuwah.dicoding.muvilog.services

import android.content.Intent
import android.widget.RemoteViewsService
import dev.hyuwah.dicoding.muvilog.presentation.widget.FavoritesRemoteViewFactory

class FavoritesWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return FavoritesRemoteViewFactory(this.applicationContext)
    }
}