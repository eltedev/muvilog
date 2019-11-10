package dev.hyuwah.dicoding.muvilog.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.hyuwah.dicoding.muvilog.services.NotificationAlarmService

@Module
abstract class NotificationModule {

    @ContributesAndroidInjector
    abstract fun notificationAlarmService(): NotificationAlarmService

}