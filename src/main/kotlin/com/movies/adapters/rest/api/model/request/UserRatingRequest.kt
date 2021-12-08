package com.movies.adapters.rest.api.model.request

import com.movies.domain.entity.Movie
import com.movies.domain.entity.Moviegoer
import com.movies.domain.entity.UserRating
import com.movies.domain.vo.StringVO

data class UserRatingRequest(
    val rating: String,
    val comments: String,
    val moviegoerId: String
) {

    fun toEntity(movie: Movie) =
        UserRating(
            rating = StringVO(rating, 1, 10, "moviegoerRating"),
            comments = StringVO(comments, 1, 500, "moviegoerComments"),
            moviegoer = Moviegoer(userName = StringVO(moviegoerId, 1, 50, "userName")),
            movie = movie
        )
}