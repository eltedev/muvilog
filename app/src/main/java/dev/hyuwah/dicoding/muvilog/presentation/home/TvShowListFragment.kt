package dev.hyuwah.dicoding.muvilog.presentation.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.data.DataHelper
import dev.hyuwah.dicoding.muvilog.presentation.detail.MovieDetailActivity
import kotlinx.android.synthetic.main.fragment_tv_show_list.*

class TvShowListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TvShowsAdapter {
            val intent = Intent(
                requireContext(),
                MovieDetailActivity::class.java
            ).apply {
                putExtra(
                    MovieDetailActivity.TV_SHOW_KEY,
                    it
                )
            }
            startActivity(intent)
        }
        adapter.setTvShowList(DataHelper.generateTvShowList())
        rv_tv_show_list.layoutManager = LinearLayoutManager(requireContext())
        rv_tv_show_list.adapter = adapter

    }


}
