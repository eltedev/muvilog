package dev.hyuwah.dicoding.muvilog.presentation.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.presentation.detail.MovieDetailActivity
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource
import dev.hyuwah.dicoding.muvilog.presentation.shared.MovieTvListAdapter
import dev.hyuwah.dicoding.muvilog.setGone
import dev.hyuwah.dicoding.muvilog.setVisible
import kotlinx.android.synthetic.main.favorite_list_fragment.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class FavoriteListFragment : DaggerFragment(), MovieTvListAdapter.Interaction {

    @Inject lateinit var viewModel: FavoriteListViewModel
    private lateinit var adapter: MovieTvListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (act as BaseActivity).setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.favorite)

        rv_favorite.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        adapter = MovieTvListAdapter(this)

        rv_favorite.adapter = adapter

        viewModel.state.observe(this, ::updateUI)
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    override fun onItemSelected(position: Int, item: FavoriteMovie) {

        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra("movie_detail", item.toMovieItem())
        intent.putExtra("category", item.category)
        startActivity(intent)
    }

    private fun updateUI(state: Resource<List<FavoriteMovie>>){
        when (state) {
            is Resource.Loading -> onLoading()
            is Resource.Success -> onLoadSuccess(state.data)
            is Resource.Failure -> onLoadFailure()
        }
    }

    private fun onLoading() {

    }

    private fun onLoadSuccess(favs: List<FavoriteMovie>) {
        if (favs.isNotEmpty()) {
            tv_empty_view.setGone()
            adapter.submitList(favs)
        } else {
            adapter.submitList(emptyList())
            tv_empty_view.setVisible()
        }
    }

    private fun onLoadFailure() {
    }

}
