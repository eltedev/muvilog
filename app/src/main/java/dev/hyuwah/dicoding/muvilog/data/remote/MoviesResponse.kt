package dev.hyuwah.dicoding.muvilog.data.remote


import com.squareup.moshi.Json
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem

data class MoviesResponse(
    @Json(name = "dates")
    val dates: Dates = Dates(),
    @Json(name = "page")
    val page: Int = 0,
    @Json(name = "results")
    val results: List<Result> = listOf(),
    @Json(name = "total_pages")
    val totalPages: Int = 0,
    @Json(name = "total_results")
    val totalResults: Int = 0
) {
    data class Result(
        @Json(name = "adult")
        val adult: Boolean = false,
        @Json(name = "backdrop_path")
        val backdropPath: String = "",
        @Json(name = "genre_ids")
        val genreIds: List<Int> = listOf(),
        @Json(name = "id")
        val id: Int = 0,
        @Json(name = "original_language")
        val originalLanguage: String = "",
        @Json(name = "original_title")
        val originalTitle: String = "",
        @Json(name = "overview")
        val overview: String = "",
        @Json(name = "popularity")
        val popularity: Double = 0.0,
        @Json(name = "poster_path")
        val posterPath: String = "",
        @Json(name = "release_date")
        val releaseDate: String = "",
        @Json(name = "title")
        val title: String = "",
        @Json(name = "video")
        val video: Boolean = false,
        @Json(name = "vote_average")
        val voteAverage: Double = 0.0,
        @Json(name = "vote_count")
        val voteCount: Int = 0
    )

    data class Dates(
        @Json(name = "maximum")
        val maximum: String = "",
        @Json(name = "minimum")
        val minimum: String = ""
    )

}

fun MoviesResponse.toPresentation() : List<MovieItem> {
    return this.results.map {
        MovieItem(
            it.id,
            it.title,
            it.posterPath,
            it.backdropPath,
            it.releaseDate,
            it.overview,
            it.voteAverage,
            it.voteCount
        )
    }
}