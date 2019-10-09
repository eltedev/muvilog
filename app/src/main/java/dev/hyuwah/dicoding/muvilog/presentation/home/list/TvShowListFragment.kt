package dev.hyuwah.dicoding.muvilog.presentation.home.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.home.adapter.TvShowsAdapter
import dev.hyuwah.dicoding.muvilog.presentation.home.detail.MovieDetailActivity
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource
import kotlinx.android.synthetic.main.fragment_tv_show_list.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class TvShowListFragment : Fragment() {

    private lateinit var viewModel: TvShowListViewModel
    private lateinit var adapter: TvShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TvShowsAdapter {
            startActivity<MovieDetailActivity>(MovieDetailActivity.TV_SHOW_KEY to it)
        }

        rv_tv_show_list.layoutManager = LinearLayoutManager(requireContext())
        rv_tv_show_list.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(TvShowListViewModel::class.java)
        viewModel.state.observe(this, ::updateUI)
        viewModel.load()

        srl_tv_show_list.setOnRefreshListener { viewModel.load() }
    }

    private fun updateUI(resource: Resource<List<MovieItem>>){
        showNoInternetView(false)
        when(resource){
            is Resource.Loading -> {
                showLoading(true)
            }
            is Resource.Success -> {
                showLoading(false)
                adapter.setTvShowList(resource.data)
            }
            is Resource.Failure -> {
                showLoading(false)
                showNoInternetView(true)
                toast("Error ${resource.throwable.localizedMessage}")
            }
        }
    }

    private fun showNoInternetView(toggle: Boolean){
        tv_no_internet.visibility = if(toggle) View.VISIBLE else View.GONE
    }

    private fun showLoading(toggle: Boolean){
        srl_tv_show_list.isRefreshing = toggle
    }

}
