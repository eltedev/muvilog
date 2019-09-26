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

class TvShowsAdapter(var onClick: (MovieItem) -> Unit) :
    RecyclerView.Adapter<TvShowsAdapter.ViewHolder>() {

    private var tvShows = arrayListOf<MovieItem>()

    fun setTvShowList(newTVShowList: List<MovieItem>){
        tvShows.clear()
        tvShows.addAll(newTVShowList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_main_movies, parent, false))
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tvShows[position], onClick)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tvShow: MovieItem, onClick: (MovieItem) -> Unit) = with(itemView) {
            setOnClickListener { onClick(tvShow) }
            tv_list_title.text = tvShow.title
            tv_list_title.isSelected = true
            Glide.with(itemView).load(tvShow.posterUrl).into(iv_list_poster)
            tv_list_rating.text = "${tvShow.voteAverage}"
            tv_list_genre.text = "${tvShow.voteCount} voters"
            tv_list_release_date.text = tvShow.releaseDate.toNormalDateFormat()
        }
    }
}