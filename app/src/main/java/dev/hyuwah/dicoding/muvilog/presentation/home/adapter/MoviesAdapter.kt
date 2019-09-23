package dev.hyuwah.dicoding.muvilog.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import dev.hyuwah.dicoding.muvilog.toNormalDateFormat
import kotlinx.android.synthetic.main.row_main_movies.view.*

class MoviesAdapter(var onClick: (MovieItem) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var movies = arrayListOf<MovieItem>()

    fun setMovieList(newMovies: List<MovieItem>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_main_movies,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], onClick)
    }

    inner class ViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: MovieItem, onClick: (MovieItem) -> Unit) = with(view) {
            cv_container.setOnClickListener { onClick(movie) }
            tv_list_title.text = movie.title
            tv_list_title.isSelected = true
            Glide.with(itemView).load(movie.posterUrl).into(iv_list_poster)
            tv_list_rating.text = "${movie.voteAverage}"
            tv_list_genre.text = "${movie.voteCount} voters"
            tv_list_release_date.text = movie.releaseDate.toNormalDateFormat()
        }
    }
}