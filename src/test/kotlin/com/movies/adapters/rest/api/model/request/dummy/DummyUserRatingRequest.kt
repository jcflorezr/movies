package com.movies.adapters.rest.api.model.request.dummy

import com.movies.adapters.rest.api.model.request.UserRatingRequest

class DummyUserRatingRequest {

    companion object {
        fun createNew(
            rating: String = "Awesome",
            comments: String = "The best movie",
            moviegoerId: String = "Moviegoer1"
        ): UserRatingRequest = UserRatingRequest(rating, comments, moviegoerId)
    }
}