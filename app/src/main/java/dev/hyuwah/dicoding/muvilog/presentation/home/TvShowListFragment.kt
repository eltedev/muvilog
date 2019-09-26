package dev.hyuwah.dicoding.muvilog.presentation.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.detail.MovieDetailActivity
import dev.hyuwah.dicoding.muvilog.presentation.home.adapter.TvShowsAdapter
import kotlinx.android.synthetic.main.fragment_tv_show_list.*
import org.jetbrains.anko.support.v4.startActivity

class TvShowListFragment : Fragment() {

    private lateinit var viewModel: TvShowListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TvShowsAdapter {
            startActivity<MovieDetailActivity>(
                MovieDetailActivity.TV_SHOW_KEY to it
            )
        }

        showLoading(true)

        rv_tv_show_list.layoutManager = LinearLayoutManager(requireContext())
        rv_tv_show_list.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(TvShowListViewModel::class.java)
        viewModel.discoverTvShow.observe(this, Observer {
            showLoading(false)
            println("Tv Show: $it")
            adapter.setTvShowList(it)
        })
    }

    private fun showLoading(toggle: Boolean){
        pb_loading.visibility = if(toggle) View.VISIBLE else View.GONE
    }

}
