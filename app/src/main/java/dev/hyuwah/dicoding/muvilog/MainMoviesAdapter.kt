package dev.hyuwah.dicoding.muvilog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.row_main_movies.view.*

class MainMoviesAdapter(var context: Context, var onClick: (Movie) -> Unit) : BaseAdapter() {

    var movies = arrayListOf<Movie>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.row_main_movies, parent, false)

        ViewHolder(view).apply {
            bind(movies[position], onClick)
        }

        return view

    }

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = movies.size

    inner class ViewHolder(var view: View) {

        fun bind(movie: Movie, onClick: (Movie) -> Unit) = with(view) {
            cv_container.setOnClickListener { onClick.invoke(movie) }
            tv_list_title.text = movie.title
            tv_list_title.isSelected = true
            iv_list_poster.setImageResource(movie.poster)
            tv_list_rating.text = movie.rating
            tv_list_genre.text = movie.genre
            tv_list_runtime.text = "${movie.runtime} mins"
        }
    }
}