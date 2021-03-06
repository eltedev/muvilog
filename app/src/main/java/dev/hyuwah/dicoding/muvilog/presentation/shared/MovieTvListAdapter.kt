package dev.hyuwah.dicoding.muvilog.presentation.shared

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.data.local.entity.FavoriteMovie
import dev.hyuwah.dicoding.muvilog.load
import dev.hyuwah.dicoding.muvilog.toNormalDateFormat
import kotlinx.android.synthetic.main.row_main_movies.view.*
import kotlinx.android.synthetic.main.row_movie_tv_item.view.*
import kotlinx.android.synthetic.main.row_movie_tv_item.view.cv_container
import kotlinx.android.synthetic.main.row_movie_tv_item.view.iv_list_poster
import kotlinx.android.synthetic.main.row_movie_tv_item.view.tv_list_genre
import kotlinx.android.synthetic.main.row_movie_tv_item.view.tv_list_rating
import kotlinx.android.synthetic.main.row_movie_tv_item.view.tv_list_release_date
import kotlinx.android.synthetic.main.row_movie_tv_item.view.tv_list_title
import kotlinx.android.synthetic.main.row_movie_tv_item.view.tv_overview

class MovieTvListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteMovie>() {

        override fun areItemsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_movie_tv_item, parent, false),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> { holder.bind(differ.currentList[position]) }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<FavoriteMovie>) {
        differ.submitList(list)
    }

    class ViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: FavoriteMovie) = with(itemView) {
            cv_container.setOnClickListener { interaction?.onItemSelected(adapterPosition, movie) }

            tv_favorite_category.text = movie.category

            tv_list_title.text = movie.title
            tv_list_title.isSelected = true
            iv_list_poster.load(movie.posterUrl, R.drawable.placeholder_poster_portrait)
            tv_list_rating.text = "${movie.voteAverage}"
            tv_overview.text = movie.overview
            tv_list_genre.text = String.format(context.getString(R.string.count_voters), movie.voteCount)
            tv_list_release_date.text = movie.releaseDate.toNormalDateFormat()
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: FavoriteMovie)
    }
}