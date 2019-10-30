package dev.hyuwah.dicoding.muvilog.services

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.local.AppDatabase
import dev.hyuwah.dicoding.muvilog.data.remote.TheMovieDbServicesFactory
import dev.hyuwah.dicoding.muvilog.presentation.home.MainActivity
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class NotificationAlarmService : BroadcastReceiver() {

    companion object {
        private const val TYPE = "type"

        enum class TYPES(val id: Int, val hour: Int) {
            DAILY_REMINDER(101, 7),
            RELEASE_TODAY(102, 8)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getStringExtra(TYPE)
        when (type) {
            TYPES.DAILY_REMINDER.name -> showDailyReminder(context)
            TYPES.RELEASE_TODAY.name -> showReleaseToday(context)
        }
    }

    private fun getReminderTime(type: TYPES): Calendar {
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, type.hour)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            if (before(Calendar.getInstance())) {
                add(Calendar.DATE, 1)
            }
        }
    }

    fun setReminder(context: Context, type: TYPES) {
        val intent = Intent(context, NotificationAlarmService::class.java)
        intent.putExtra(TYPE, type.name)
        val pendingIntent = PendingIntent.getBroadcast(context, type.id, intent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            getReminderTime(type).timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    fun cancelReminder(context: Context, types: TYPES) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationAlarmService::class.java)
        val requestCode = types.id
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }

    private fun showDailyReminder(context: Context) {
        val NOTIFICATION_ID = 1
        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "Daily Reminder channel"

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_movie_24dp)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_movie_24dp))
            .setColor(context.resources.getColor(R.color.colorSecondary))
            .setContentTitle(context.getString(R.string.reminder_daily_title))
            .setContentText(context.getString(R.string.reminder_daily_content_text))
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigTextStyle().bigText(context.getString(R.string.reminder_daily_content_text)))
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun showReleaseToday(context: Context) {
        val repository = Repository(
            TheMovieDbServicesFactory.create(),
            AppDatabase.getInstance(context).favoriteMovieDao()
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val movies = repository.getTodayReleasedMovies()
                var id = 2
                movies.forEach {
                    showReleaseTodayNotification(context, id, it)
                    id++
                }
            } catch (e: Throwable){

            }
        }
    }

    private fun showReleaseTodayNotification(context: Context, id: Int, movieItem: MovieItem) {
        val CHANNEL_ID = "Channel_2"
        val CHANNEL_NAME = "Release Today channel"

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_movie_24dp)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_movie_24dp))
            .setColor(context.resources.getColor(R.color.colorSecondary))
            .setContentTitle(movieItem.title)
            .setContentText(movieItem.title + context.getString(R.string.release_today_content))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.BigTextStyle().bigText(movieItem.overview))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManager.notify(id, notification)
    }


}