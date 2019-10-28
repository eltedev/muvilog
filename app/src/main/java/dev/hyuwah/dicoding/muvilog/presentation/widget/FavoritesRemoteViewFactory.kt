package dev.hyuwah.dicoding.muvilog.presentation.widget

import android.content.Context
import android.os.Binder
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.local.AppDatabase
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie
import dev.hyuwah.dicoding.muvilog.data.remote.TheMovieDbServicesFactory

class FavoritesRemoteViewFactory(private val context: Context): RemoteViewsService.RemoteViewsFactory {

    private val widgetItems = ArrayList<FavoriteMovie>()
    private val repository = Repository(
        TheMovieDbServicesFactory.create(),
        AppDatabase.getInstance(context).favoriteMovieDao()
    )

    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        val identityToken = Binder.clearCallingIdentity()
        widgetItems.clear()
        repository.getAllFavorite().forEach {
            widgetItems.add(it)
        }
        Binder.restoreCallingIdentity(identityToken)
    }

    override fun onDestroy() {

    }

    override fun getViewAt(position: Int): RemoteViews {
        val view = RemoteViews(context.packageName, R.layout.favorites_widget_item)
        try {
            val movieItem = widgetItems[position]
            view.setTextViewText(R.id.tv_favorites_item_title, movieItem.title)

            val bitmap = Glide.with(context).asBitmap().load(movieItem.backdropUrl)
                .apply(RequestOptions.fitCenterTransform())
                .submit().get()
            view.setImageViewBitmap(R.id.iv_favorite_item, bitmap)

            // TODO SEND PARCELABLE OR SEND ID
//            val extras = Bundle()
//            extras.putParcelable(FavoritesWidget.EXTRA_ITEM, movieItem)
//            val fillIntent = Intent()
//            fillIntent.putExtra(FavoritesWidget.EXTRA_ITEM, movieItem)
//            fillIntent.putExtras(extras)
//
//            view.setOnClickFillInIntent(R.id.iv_favorite_item, fillIntent)

        } catch (e: Throwable) {
            e.printStackTrace()
        }

        return view
    }

    override fun getCount(): Int = widgetItems.size

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = 0

    override fun hasStableIds(): Boolean = false
}
