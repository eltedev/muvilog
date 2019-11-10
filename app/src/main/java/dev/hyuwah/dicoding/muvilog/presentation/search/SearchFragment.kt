package dev.hyuwah.dicoding.muvilog.presentation.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.data.Constants
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.presentation.detail.MovieDetailActivity
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource
import dev.hyuwah.dicoding.muvilog.presentation.shared.MovieTvListAdapter
import dev.hyuwah.dicoding.muvilog.setGone
import dev.hyuwah.dicoding.muvilog.setVisible
import kotlinx.android.synthetic.main.fragment_search.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class SearchFragment : DaggerFragment(), MovieTvListAdapter.Interaction {

    @Inject lateinit var viewModel: SearchViewModel
    private lateinit var adapter: MovieTvListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (act as BaseActivity).setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.search)

        srl_search.isEnabled = false

        rv_search.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        adapter = MovieTvListAdapter(this)

        rv_search.adapter = adapter

        viewModel.state.observe(this, ::updateUI)

        searchview.setOnQueryTextListener(searchListener)

    }

    private val searchListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let { viewModel.search(query) }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }

    }

    override fun onItemSelected(position: Int, item: FavoriteMovie) {
        val key =
            if (item.category == Constants.MOVIE_KEY) MovieDetailActivity.MOVIE_KEY else MovieDetailActivity.TV_SHOW_KEY
        startActivity<MovieDetailActivity>(key to item.toMovieItem())
    }

    private fun updateUI(state: Resource<List<FavoriteMovie>>) {
        when (state) {
            is Resource.Loading -> onLoading()
            is Resource.Success -> onLoadSuccess(state.data)
            is Resource.Failure -> onLoadFailure()
        }
    }

    private fun onLoading() {
        srl_search.isRefreshing = true
    }

    private fun onLoadSuccess(results: List<FavoriteMovie>) {
        srl_search.isRefreshing = false
        if (results.isNotEmpty()) {
            tv_empty_view.setGone()
            adapter.submitList(results)
        } else {
            adapter.submitList(emptyList())
            tv_empty_view.setVisible()
        }
    }

    private fun onLoadFailure() {
        srl_search.isRefreshing = false
    }


}
