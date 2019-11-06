package dev.hyuwah.dicoding.muvilog.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dev.hyuwah.dicoding.muvilog.presentation.home.list.MovieListFragment
import dev.hyuwah.dicoding.muvilog.presentation.home.list.MovieListViewModel

@Module
abstract class DiscoveryModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun movieListFragment(): MovieListFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun bindViewModel(viewModel: MovieListViewModel): ViewModel
}