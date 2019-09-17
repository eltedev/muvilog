package dev.hyuwah.dicoding.muvilog.presentation.detail

import android.os.Bundle
import androidx.annotation.DrawableRes
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.presentation.model.Movie
import dev.hyuwah.dicoding.muvilog.presentation.model.TVShow
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.view_detail_description.*
import kotlinx.android.synthetic.main.view_detail_rounded_poster_with_shadow.*

class MovieDetailActivity : BaseActivity() {

    companion object {
        const val MOVIE_KEY = "movie"
        const val TV_SHOW_KEY = "tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        title = getString(R.string.detail_activity_title)

        val movie = intent.getParcelableExtra<Movie>(MOVIE_KEY)
        movie?.let {
            setupView(it.poster, it.title, it.genre, it.runtime, it.rating, it.overview)
        }

        val tvshow = intent.getParcelableExtra<TVShow>(TV_SHOW_KEY)
        tvshow?.let {
            setupView(it.poster, it.title, it.genre, it.runtime, it.rating, it.overview)
        }
    }

    private fun setupView(@DrawableRes poster: Int, title: String, genre: String, runtime: Int, rating: String, overview:String){
        iv_detail_poster.setImageResource(poster)
        tv_detail_title.text = title
        tv_detail_genre.text = genre
        tv_detail_runtime.text = getString(R.string.detail_runtime, runtime)
        tv_detail_rating.text = getString(R.string.detail_rating, rating)
        tv_detail_overview.text = overview
    }
}
