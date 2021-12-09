package com.movies.adapters.rest.api.model.request.dummy

import com.movies.adapters.rest.api.model.request.MoviegoerRequest

class DummyMoviegoerRequest {

    companion object {
        fun createNew(moviegoerName: String = "Moviegoer1"): MoviegoerRequest =
            MoviegoerRequest(userName = moviegoerName)
    }
}
