package dev.hyuwah.dicoding.muvilog.data.remote.model


import com.squareup.moshi.Json
import dev.hyuwah.dicoding.muvilog.asBackdropUrl
import dev.hyuwah.dicoding.muvilog.asPosterUrl
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem

data class DiscoverTvResponse(
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
        @Json(name = "backdrop_path")
        val backdropPath: String? = "",
        @Json(name = "first_air_date")
        val firstAirDate: String = "",
        @Json(name = "genre_ids")
        val genreIds: List<Int> = listOf(),
        @Json(name = "id")
        val id: Int = 0,
        @Json(name = "name")
        val name: String = "",
        @Json(name = "origin_country")
        val originCountry: List<String> = listOf(),
        @Json(name = "original_language")
        val originalLanguage: String = "",
        @Json(name = "original_name")
        val originalName: String = "",
        @Json(name = "overview")
        val overview: String = "",
        @Json(name = "popularity")
        val popularity: Double = 0.0,
        @Json(name = "poster_path")
        val posterPath: String? = "",
        @Json(name = "vote_average")
        val voteAverage: Double = 0.0,
        @Json(name = "vote_count")
        val voteCount: Int = 0
    )
}

fun DiscoverTvResponse.toPresentation() : List<MovieItem> {
    return this.results.map {
        MovieItem(
            it.id,
            it.name,
            it.posterPath.orEmpty().asPosterUrl(),
            it.backdropPath.orEmpty().asBackdropUrl(),
            it.firstAirDate,
            it.overview,
            it.voteAverage,
            it.voteCount
        )
    }
}