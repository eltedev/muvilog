package dev.hyuwah.dicoding.muvilog.presentation.home.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.detail.MovieDetailActivity
import dev.hyuwah.dicoding.muvilog.presentation.home.adapter.TvShowsAdapter
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource
import dev.hyuwah.dicoding.muvilog.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.fragment_tv_show_list.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class TvShowListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: TvShowListViewModel
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
            //startActivity<MovieDetailActivity>(MovieDetailActivity.NOW_PLAYING_KEY to it)
        }

        rv_tv_show_list.layoutManager = LinearLayoutManager(requireContext())
        rv_tv_show_list.adapter = adapter

        viewModel.state.observe(this, ::updateUI)
        if (savedInstanceState==null) viewModel.load()

        srl_tv_show_list.setOnRefreshListener { viewModel.load() }
    }

    private fun updateUI(resource: Resource<List<MovieItem>>){
        showNoInternetView(false)
        when(resource){
            is Resource.Loading -> {
                EspressoIdlingResource.increment()
                showLoading(true)
            }
            is Resource.Success -> {
                showLoading(false)
                adapter.setTvShowList(resource.data)
                EspressoIdlingResource.decrement()
            }
            is Resource.Failure -> {
                showLoading(false)
                showNoInternetView(true)
                toast("Error ${resource.throwable.localizedMessage}")
                EspressoIdlingResource.decrement()
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
