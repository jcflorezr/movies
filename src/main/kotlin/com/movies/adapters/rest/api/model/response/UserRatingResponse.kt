package com.movies.adapters.rest.api.model.response

import com.movies.domain.entity.UserRating

data class UserRatingResponse(
    val rating: String,
    val comments: String,
    val moviegoer: MoviegoerResponse
) {

    companion object {
        fun fromEntity(userRating: UserRating): UserRatingResponse = userRating.run {
            UserRatingResponse(
                rating = rating.value,
                comments = comments.value,
                moviegoer = MoviegoerResponse.fromEntity(moviegoer)
            )
        }
    }
}