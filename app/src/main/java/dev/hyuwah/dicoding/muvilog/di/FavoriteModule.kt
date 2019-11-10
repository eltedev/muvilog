package dev.hyuwah.dicoding.muvilog.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dev.hyuwah.dicoding.muvilog.presentation.favorite.FavoriteListFragment
import dev.hyuwah.dicoding.muvilog.presentation.favorite.FavoriteListViewModel

@Module
abstract class FavoriteModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun favoriteListFragment(): FavoriteListFragment

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteListViewModel::class)
    internal abstract fun bindViewModel(viewModel: FavoriteListViewModel): ViewModel

}