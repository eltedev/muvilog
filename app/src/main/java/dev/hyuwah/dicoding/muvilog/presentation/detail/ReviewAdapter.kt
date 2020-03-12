package dev.hyuwah.dicoding.muvilog.presentation.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.fromHtml
import dev.hyuwah.dicoding.muvilog.presentation.model.ReviewItem
import kotlinx.android.synthetic.main.row_review.view.*

class ReviewAdapter(private var onClick: (ReviewItem) -> Unit) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ReviewItem>() {
        override fun areContentsTheSame(oldItem: ReviewItem, newItem: ReviewItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: ReviewItem, newItem: ReviewItem): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun setReviewList(newReviews: List<ReviewItem>) {
        differ.submitList(newReviews)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_review, parent, false)
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position], onClick)
    }

    inner class ViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(review: ReviewItem, onClick: (ReviewItem) -> Unit) = with(view) {
            cv_container.setOnClickListener {

            }
            tv_list_author.text = review.author
            tv_list_content.text = fromHtml(review.content)
        }
    }
}