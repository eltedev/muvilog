package dev.hyuwah.dicoding.muvilog.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.presentation.home.detail.MovieDetailActivity
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource
import dev.hyuwah.dicoding.muvilog.setGone
import dev.hyuwah.dicoding.muvilog.setVisible
import kotlinx.android.synthetic.main.favorite_list_fragment.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.startActivity

class FavoriteListFragment : Fragment(), FavoriteListAdapter.Interaction {

    private lateinit var viewModel: FavoriteListViewModel
    private lateinit var adapter: FavoriteListAdapter

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

        adapter = FavoriteListAdapter(this)

        rv_favorite.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(FavoriteListViewModel::class.java)
        viewModel.state.observe(this, ::updateUI)
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    override fun onItemSelected(position: Int, item: FavoriteMovie) {
        val key =
            if (item.category == "movie") MovieDetailActivity.MOVIE_KEY else MovieDetailActivity.TV_SHOW_KEY
        startActivity<MovieDetailActivity>(key to item.toMovieItem())
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
