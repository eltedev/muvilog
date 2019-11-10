package dev.hyuwah.dicoding.muvilog.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dev.hyuwah.dicoding.muvilog.presentation.detail.MovieDetailActivity
import dev.hyuwah.dicoding.muvilog.presentation.detail.MovieDetailViewModel
import dev.hyuwah.dicoding.muvilog.presentation.home.list.MovieListFragment
import dev.hyuwah.dicoding.muvilog.presentation.home.list.MovieListViewModel
import dev.hyuwah.dicoding.muvilog.presentation.home.list.TvShowListFragment
import dev.hyuwah.dicoding.muvilog.presentation.home.list.TvShowListViewModel

@Module
abstract class DiscoveryModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun movieListFragment(): MovieListFragment

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun tvshowListFragment(): TvShowListFragment

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun movieDetailActivity(): MovieDetailActivity

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun bindMovieViewModel(viewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvShowListViewModel::class)
    internal abstract fun bindTvShowViewModel(viewModel: TvShowListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel
}