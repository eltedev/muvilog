package dev.hyuwah.dicoding.muvilog.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dev.hyuwah.dicoding.muvilog.presentation.search.SearchFragment
import dev.hyuwah.dicoding.muvilog.presentation.search.SearchViewModel

@Module
abstract class SearchModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun searchFragment(): SearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindViewModel(viewModel: SearchViewModel): ViewModel
}