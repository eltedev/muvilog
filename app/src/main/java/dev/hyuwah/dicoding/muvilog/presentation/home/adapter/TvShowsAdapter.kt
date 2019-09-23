package dev.hyuwah.dicoding.muvilog.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.model.TVShow
import kotlinx.android.synthetic.main.row_main_movies.view.*

class TvShowsAdapter(var onClick: (TVShow) -> Unit) :
    RecyclerView.Adapter<TvShowsAdapter.ViewHolder>() {

    private var tvShows = arrayListOf<TVShow>()

    fun setTvShowList(newTVShowList: MutableList<TVShow>){
        tvShows.clear()
        tvShows.addAll(newTVShowList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_main_movies, parent, false))
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tvShows[position], onClick)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tvShow: TVShow, onClick: (TVShow) -> Unit) = with(itemView) {
            setOnClickListener { onClick(tvShow) }
            tv_list_title.text = tvShow.title
            tv_list_genre.text = tvShow.genre
            tv_list_rating.text = tvShow.rating
            tv_list_release_date.text = itemView.context.getString(R.string.runtime_mins, tvShow.runtime)
            iv_list_poster.setImageResource(tvShow.poster)
        }
    }
}