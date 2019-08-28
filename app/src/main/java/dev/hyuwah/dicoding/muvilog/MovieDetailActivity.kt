package dev.hyuwah.dicoding.muvilog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_KEY = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        title = "Detail"
        val movie = intent.getParcelableExtra<Movie>(MOVIE_KEY)

        iv_detail_poster.setImageResource(movie.poster)
        tv_detail_title.text = movie.title
        tv_detail_genre.text = movie.genre
        tv_detail_runtime.text = "Runtime: ${movie.runtime} mins"
        tv_detail_rating.text = "Rating: ${movie.rating}"
        tv_detail_overview.text = movie.overview

    }
}
