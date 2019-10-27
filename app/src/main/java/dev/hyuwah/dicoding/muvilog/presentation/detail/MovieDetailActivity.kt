package dev.hyuwah.dicoding.muvilog.presentation.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import dev.hyuwah.dicoding.muvilog.presentation.widget.FavoritesWidget
import dev.hyuwah.dicoding.muvilog.toNormalDateFormat
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.view_detail_description.*
import kotlinx.android.synthetic.main.view_detail_rounded_poster_with_shadow.*
import org.jetbrains.anko.toast

class MovieDetailActivity : BaseActivity() {

    companion object {
        const val MOVIE_KEY = "movie"
        const val TV_SHOW_KEY = "tvshow"
    }

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)

        val movie = intent.getParcelableExtra<MovieItem>(MOVIE_KEY)
        movie?.let {
            supportActionBar?.title = it.title
            type = getString(R.string.tab_title_movie)
            viewModel.setMovieAndType(it, MOVIE_KEY)
            setupView(it)
        }

        val tvShow = intent.getParcelableExtra<MovieItem>(TV_SHOW_KEY)
        tvShow?.let {
            supportActionBar?.title = it.title
            type = getString(R.string.tab_title_tv_show)
            viewModel.setMovieAndType(it, TV_SHOW_KEY)
            setupView(it)
        }

        viewModel.isFavorite().observe(this, Observer { isFavorite ->
            if (isFavorite) {
                fab_favorite.setImageResource(R.drawable.ic_favorite_24dp)
                fab_favorite.setOnClickListener {
                    toast(getString(R.string.notify_removed_from_favorite))
                    viewModel.removeFromFavorite()
                }
            } else {
                fab_favorite.setImageResource(R.drawable.ic_favorite_border_24dp)
                fab_favorite.setOnClickListener {
                    toast(getString(R.string.notify_saved_to_favorite))
                    viewModel.saveToFavorite()
                }
            }
        })
    }

    private fun setupView(movieItem: MovieItem){
        Glide.with(this).load(movieItem.posterUrl).into(iv_detail_poster)
        Glide.with(this).load(movieItem.backdropUrl).into(iv_backdrop)
        tv_detail_title.text = type
        tv_detail_genre.text = String.format(getString(R.string.count_voters), movieItem.voteCount)
        tv_detail_runtime.text = movieItem.releaseDate.toNormalDateFormat()
        tv_detail_rating.text = movieItem.voteAverage.toString()
        tv_detail_overview.text = movieItem.overview
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
