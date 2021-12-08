package com.movies.adapters.rest.api.model.response

import com.movies.domain.entity.MovieCatalogInfo

data class MovieCatalogInfoResponse (
    val price: String,
    val showTime: String,
    val movie: MovieResponse
) {

    companion object {
        fun fromEntity(movieCatalogInfo: MovieCatalogInfo): MovieCatalogInfoResponse =
            movieCatalogInfo.run {
                MovieCatalogInfoResponse(
                    price = price.value,
                    showTime = showTime.value,
                    movie = MovieResponse.fromEntity(movie)
                )
            }
    }
}