package dev.hyuwah.dicoding.muvilog.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.hyuwah.dicoding.muvilog.MuvilogApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        DataModule::class,
        DiscoveryModule::class,
        SearchModule::class,
        FavoriteModule::class,
        NotificationModule::class
    ]
)
interface AppComponent : AndroidInjector<MuvilogApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}