package com.movies.adapters.rest.api.model.request

import com.movies.domain.entity.Movie
import com.movies.domain.entity.MovieCatalogInfo
import com.movies.domain.vo.StringVO

data class MovieCatalogInfoRequest (
    val price: String,
    val showTime: String
) {
    fun toEntity(movie: Movie) =
        MovieCatalogInfo(
            movie = movie,
            price = StringVO(price, 1, 500, "moviePrice"),
            showTime = StringVO(showTime, 1, 500, "movieShowTime")
        )
}