package dev.hyuwah.dicoding.muvilog.presentation.home


import android.content.Intent
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

class MovieListFragment : Fragment() {

    lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MoviesAdapter {
            val intent = Intent(
                requireContext(),
                MovieDetailActivity::class.java
            ).apply {
                putExtra(
                    MovieDetailActivity.MOVIE_KEY,
                    it
                )
            }
            startActivity(intent)
        }

        rv_movie_list.layoutManager = LinearLayoutManager(requireContext())
        rv_movie_list.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        viewModel.discoverMovies.observe(this, Observer {
            println("Item: $it")
            adapter.setMovieList(it)
        })

    }

}
