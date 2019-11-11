package dev.hyuwah.dicoding.muvilog.data.helper

import dev.hyuwah.dicoding.muvilog.data.remote.model.DiscoverMoviesResponse
import dev.hyuwah.dicoding.muvilog.data.remote.model.DiscoverTvResponse
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem

object DummyData {

    object MovieList {
        fun normal(): List<MovieItem> {
            return listOf(
                MovieItem(
                    id = 1,
                    title = "Movie Test",
                    voteCount = 1,
                    voteAverage = 10.0,
                    overview = "Overview",
                    backdropUrl = "",
                    posterUrl = "",
                    releaseDate = ""
                )
            )
        }
    }

    object DiscoverMovie {
        fun emptyResponse(): DiscoverMoviesResponse {
            return DiscoverMoviesResponse()
        }

        fun normalResponse(): DiscoverMoviesResponse {
            return DiscoverMoviesResponse(
                results = listOf(
                    DiscoverMoviesResponse.Result(
                        title = "Movie 1"
                    )
                )
            )
        }
    }

    object DiscoverTv {
        fun emptyResponse(): DiscoverTvResponse {
            return DiscoverTvResponse()
        }

        fun normalResponse(): DiscoverTvResponse {
            return DiscoverTvResponse(
                results = listOf(
                    DiscoverTvResponse.Result(
                        name = "TV Show 1"
                    )
                )
            )
        }
    }


}