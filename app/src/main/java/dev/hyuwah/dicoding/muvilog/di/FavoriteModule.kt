package dev.hyuwah.dicoding.muvilog.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dev.hyuwah.dicoding.muvilog.presentation.favorite.FavoriteListFragment
import dev.hyuwah.dicoding.muvilog.presentation.favorite.FavoriteListViewModel
import dev.hyuwah.dicoding.muvilog.services.FavoritesWidgetService

@Module
abstract class FavoriteModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun favoriteListFragment(): FavoriteListFragment

    @ContributesAndroidInjector
    internal abstract fun favoriteWidgetService(): FavoritesWidgetService

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteListViewModel::class)
    internal abstract fun bindViewModel(viewModel: FavoriteListViewModel): ViewModel

}