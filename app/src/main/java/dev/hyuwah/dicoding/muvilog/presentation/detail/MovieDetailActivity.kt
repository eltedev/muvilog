package dev.hyuwah.dicoding.muvilog.presentation.detail

import android.os.Bundle
import com.bumptech.glide.Glide
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import dev.hyuwah.dicoding.muvilog.toNormalDateFormat
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

        val movie = intent.getParcelableExtra<MovieItem>(MOVIE_KEY)
        movie?.let {
            setupView(it)
        }

        val tvShow = intent.getParcelableExtra<MovieItem>(TV_SHOW_KEY)
        tvShow?.let {
            setupView(it)
        }
    }

    private fun setupView(movieItem: MovieItem){
        Glide.with(this).load(movieItem.posterUrl).into(iv_detail_poster)
        tv_detail_title.text = movieItem.title
        tv_detail_genre.text = "${movieItem.voteCount} voters"
        tv_detail_runtime.text = movieItem.releaseDate.toNormalDateFormat()
        tv_detail_rating.text = movieItem.voteAverage.toString()
        tv_detail_overview.text = movieItem.overview
    }
}
