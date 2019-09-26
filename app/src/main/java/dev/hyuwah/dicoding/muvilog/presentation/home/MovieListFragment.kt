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
import dev.hyuwah.dicoding.muvilog.presentation.home.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.jetbrains.anko.support.v4.startActivity

class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MoviesAdapter {
            startActivity<MovieDetailActivity>(
                MovieDetailActivity.MOVIE_KEY to it
            )
        }

        rv_movie_list.layoutManager = LinearLayoutManager(requireContext())
        rv_movie_list.adapter = adapter

        showLoading(true)

        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        viewModel.discoverMovies.observe(this, Observer {
            showLoading(false)
            println("Item: $it")
            adapter.setMovieList(it)
        })

    }

    private fun showLoading(toggle: Boolean){
        pb_loading.visibility = if(toggle) View.VISIBLE else View.GONE
    }

}
