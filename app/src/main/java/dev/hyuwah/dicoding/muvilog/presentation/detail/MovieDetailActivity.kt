package dev.hyuwah.dicoding.muvilog.presentation.detail

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.load
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import dev.hyuwah.dicoding.muvilog.presentation.model.ReviewItem
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource
import dev.hyuwah.dicoding.muvilog.presentation.widget.FavoritesWidget
import dev.hyuwah.dicoding.muvilog.toNormalDateFormat
import dev.hyuwah.dicoding.muvilog.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.view_detail_description.*
import kotlinx.android.synthetic.main.view_detail_review.*
import kotlinx.android.synthetic.main.view_detail_rounded_poster_with_shadow.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import javax.inject.Inject


class MovieDetailActivity : BaseActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

//    companion object {
//        const val POPULAR_KEY = "popular"
//        const val NOW_PLAYING_KEY = "now_playing"
//        const val TOP_RATED_KEY = "top_rated"
//        const val UPCOMING_KEY = "upcoming"
//    }

    @Inject lateinit var viewModel: MovieDetailViewModel
    private lateinit var type: String

    @Inject lateinit var reviewViewModel: ReviewListViewModel

    private lateinit var adapter: ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        if (intent.hasExtra("movie_detail")){
            val movie = intent.getParcelableExtra<MovieItem>("movie_detail")
            val category = intent.getStringExtra("category")
            supportActionBar?.title = movie.title
            type = getString(R.string.now_playing)
            viewModel.setMovieAndType(movie, category)
            setupView(movie)

            if (savedInstanceState==null) {
                reviewViewModel.state.observe(this, ::updateUI)
                reviewViewModel.load(movie.id.toString())
            }

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
            sendIntentToUpdateWidget()
        })

        adapter = ReviewAdapter {

        }

        val linearLayoutManager = LinearLayoutManager(this);

        rv_review.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(
            rv_review.getContext(),
            linearLayoutManager.getOrientation()
        )
        rv_review.addItemDecoration(dividerItemDecoration)

        rv_review.adapter = adapter
    }

    private fun sendIntentToUpdateWidget() {
        sendBroadcast(Intent(this, FavoritesWidget::class.java).apply {
            action = FavoritesWidget.UPDATE_WIDGET
        })
    }

    private fun setupView(movieItem: MovieItem) {
        iv_detail_poster.load(movieItem.posterUrl, R.drawable.placeholder_poster_portrait)
        iv_backdrop.load(movieItem.backdropUrl, R.drawable.placeholder_poster_landscape)
        //tv_detail_title.text = type
        tv_detail_genre.text = String.format(getString(R.string.count_voters), movieItem.voteCount)
        tv_detail_runtime.text = movieItem.releaseDate.toNormalDateFormat()
        tv_detail_rating.text = movieItem.voteAverage.toString()
        tv_detail_overview.text = movieItem.overview
        createBlurBg(movieItem.posterUrl)
    }

    private fun updateUI(resource: Resource<List<ReviewItem>>){
        showNoInternetView(false)
        when(resource){
            is Resource.Loading -> {
                EspressoIdlingResource.increment()
                showLoading(true)
            }
            is Resource.Success -> {
                EspressoIdlingResource.decrement()
                showLoading(false)
                adapter.setReviewList(resource.data)
            }
            is Resource.Failure -> {
                EspressoIdlingResource.decrement()
                showLoading(false)
                showNoInternetView(true)
                toast("Error ${resource.throwable.localizedMessage}")
            }
        }
    }

    private fun showNoInternetView(toggle: Boolean){
        tv_no_internet.visibility = if(toggle) View.VISIBLE else View.GONE
    }

    private fun showLoading(toggle: Boolean){
        //srl_movie_list.isRefreshing = toggle
    }

    private fun createBlurBg(posterUrl: String) {
        doAsync {
            val drawable =
                Glide.with(this@MovieDetailActivity).load(posterUrl).submit().get()
            runOnUiThread {
                Palette.from(drawable.toBitmap()).generate { palette ->
                    val defValue = 0x000000
                    val dominant = palette?.getDominantColor(defValue) ?: defValue
                    val muted = palette?.getMutedColor(defValue) ?: defValue
                    val darkVibrant = palette?.getDarkMutedColor(defValue) ?: defValue
                    val vibrant = palette?.getVibrantColor(defValue) ?: defValue
                    val gradient = GradientDrawable(
                        GradientDrawable.Orientation.TL_BR,
                        intArrayOf(darkVibrant, vibrant, muted, dominant)
                    )
                    iv_bg_blur.load(gradient)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
