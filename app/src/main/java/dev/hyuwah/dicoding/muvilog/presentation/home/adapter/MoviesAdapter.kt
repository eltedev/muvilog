package dev.hyuwah.dicoding.muvilog.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import dev.hyuwah.dicoding.muvilog.toNormalDateFormat
import kotlinx.android.synthetic.main.row_main_movies.view.*

class MoviesAdapter(var onClick: (MovieItem) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun setMovieList(newMovies: List<MovieItem>) {
        differ.submitList(newMovies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_main_movies, parent, false)
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position], onClick)
    }

    inner class ViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: MovieItem, onClick: (MovieItem) -> Unit) = with(view) {
            cv_container.setOnClickListener { onClick(movie) }
            tv_list_title.text = movie.title
            tv_list_title.isSelected = true
            Glide.with(itemView).load(movie.posterUrl).into(iv_list_poster)
            tv_list_rating.text = "${movie.voteAverage}"
            tv_list_genre.text = String.format(context.getString(R.string.count_voters), movie.voteCount)
            tv_list_release_date.text = movie.releaseDate.toNormalDateFormat()
        }
    }
}