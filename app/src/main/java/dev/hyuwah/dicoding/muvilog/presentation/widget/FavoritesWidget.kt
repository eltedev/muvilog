package dev.hyuwah.dicoding.muvilog.presentation.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import androidx.core.net.toUri
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.services.FavoritesWidgetService


/**
 * Implementation of App Widget functionality.
 */
class FavoritesWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        intent.action?.let {
            if (it == TOAST_ACTION) {
                // TODO GET PARCELABLE AND SEND TO MOVIE DETAIL
                // TODO OR SEND ID AND INITIANTE DETAIL CALL API
//                val test = intent.extras?.getParcelable<FavoriteMovie>(EXTRA_ITEM)
//                val movieItem = intent.getParcelableExtra<FavoriteMovie>(EXTRA_ITEM)
//                Toast.makeText(context, "Click ${test?.id}", Toast.LENGTH_SHORT).show()
//                movieItem?.let { item ->
//                    val detailIntent = Intent(context, MovieDetailActivity::class.java)
//                    val key =
//                        if (item.category == Constants.MOVIE_KEY) Constants.MOVIE_KEY else Constants.TV_SHOW_KEY
//                    detailIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                    detailIntent.putExtra(key, item)
//                    context.startActivity(detailIntent)
//                }

            }
            if (it == UPDATE_WIDGET) {
                val appWidgetManager = AppWidgetManager.getInstance(context)
                val ids = appWidgetManager.getAppWidgetIds(
                    ComponentName(
                        context,
                        FavoritesWidget::class.java
                    )
                )
                appWidgetManager.notifyAppWidgetViewDataChanged(ids, R.id.widget_stack_favorites)
            }
        }
    }

    companion object {

        const val EXTRA_ITEM = "widget_extra_item"
        private const val TOAST_ACTION = "toast_action"
        const val UPDATE_WIDGET = "dev.hyuwah.dicoding.muvilog.UPDATE_FAVORITE"

        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {

            val intent = Intent(context, FavoritesWidgetService::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()

            // Construct the RemoteViews object
            val widgetText = context.getString(R.string.widget_title)
            val views = RemoteViews(context.packageName, R.layout.favorites_widget)
            views.setRemoteAdapter(R.id.widget_stack_favorites, intent)
            views.setEmptyView(R.id.widget_stack_favorites, R.id.widget_tv_no_data)
            views.setTextViewText(R.id.appwidget_text, widgetText)

            val toastIntent = Intent(context, FavoritesWidget::class.java)
            toastIntent.action = TOAST_ACTION
            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))
            val toastPending = PendingIntent.getBroadcast(
                context,
                0,
                toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            views.setPendingIntentTemplate(R.id.widget_stack_favorites, toastPending)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

