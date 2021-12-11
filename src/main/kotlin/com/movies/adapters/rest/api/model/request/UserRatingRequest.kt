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
            rating = StringVO(rating, 1, 500, "userRating"),
            comments = StringVO(comments, 1, 500, "userComments"),
            moviegoer = Moviegoer(userName = StringVO(moviegoerId, 2, 500, "moviegoerName")),
            movie = movie
        )
}