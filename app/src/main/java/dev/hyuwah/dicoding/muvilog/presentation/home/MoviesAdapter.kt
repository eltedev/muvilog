package dev.hyuwah.dicoding.muvilog.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.model.Movie
import kotlinx.android.synthetic.main.row_main_movies.view.*

class MoviesAdapter(var onClick: (Movie) -> Unit) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var movies = arrayListOf<Movie>()

    fun setMovieList(newMovies: List<Movie>){
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_main_movies, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], onClick)
    }

    inner class ViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie, onClick: (Movie) -> Unit) = with(view) {
            cv_container.setOnClickListener { onClick(movie) }
            tv_list_title.text = movie.title
            tv_list_title.isSelected = true
            iv_list_poster.setImageResource(movie.poster)
            tv_list_rating.text = movie.rating
            tv_list_genre.text = movie.genre
            tv_list_runtime.text = itemView.context.getString(R.string.runtime_mins, movie.runtime)
        }
    }
}