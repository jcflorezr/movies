package com.movies.adapters.rest.api.model.response

import com.movies.domain.entity.Moviegoer

data class MoviegoerResponse (
    val userName: String
) {

    companion object {

        fun fromEntity(moviegoer: Moviegoer): MoviegoerResponse = MoviegoerResponse(userName = moviegoer.userName.value)
    }
}