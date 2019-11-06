package dev.hyuwah.dicoding.muvilog.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.hyuwah.dicoding.muvilog.data.IRepository
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.local.AppDatabase
import dev.hyuwah.dicoding.muvilog.data.local.FavoriteMovieDao
import dev.hyuwah.dicoding.muvilog.data.local.SharedPrefSource
import dev.hyuwah.dicoding.muvilog.data.remote.ITheMovieDbServices
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideFavoriteMovieDao(context: Context): FavoriteMovieDao{
        return AppDatabase.getInstance(context).favoriteMovieDao()
    }

    @Singleton
    @Provides
    fun provideSharedprefSource(context: Context): SharedPrefSource {
        return SharedPrefSource(context)
    }

    @Singleton
    @Provides
    fun provideRepository(tmdbServices: ITheMovieDbServices, favoriteMovieDao: FavoriteMovieDao): IRepository {
        return Repository(tmdbServices, favoriteMovieDao)
    }
}