package dev.hyuwah.dicoding.muvilog.data.remote.model


import com.squareup.moshi.Json
import dev.hyuwah.dicoding.muvilog.asBackdropUrl
import dev.hyuwah.dicoding.muvilog.asPosterUrl
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import dev.hyuwah.dicoding.muvilog.presentation.model.ReviewItem

data class ReviewResponse(
    @Json(name = "total_results")
    val total_results: Int = 0,
    @Json(name = "total_pages")
    val total_pages: Int = 0,
    @Json(name = "page")
    val page: Int = 0,
    @Json(name = "results")
    val results: List<Result> = listOf(),
    @Json(name = "id")
    val id: Int = 0
) {
    data class Result(
        @Json(name = "author")
        val author: String = "",
        @Json(name = "content")
        val content: String = "",
        @Json(name = "id")
        val id: String = "",
        @Json(name = "url")
        val url: String = ""
    )
}

fun ReviewResponse.toPresentation() : List<ReviewItem> {
    return this.results.map {
        ReviewItem(
            it.author,
            it.content,
            it.id,
            it.url
        )
    }
}