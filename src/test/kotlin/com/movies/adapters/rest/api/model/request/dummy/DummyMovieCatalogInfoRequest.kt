package com.movies.adapters.rest.api.model.request.dummy

import com.movies.adapters.rest.api.model.request.MovieCatalogInfoRequest

class DummyMovieCatalogInfoRequest {

    companion object {

        fun createNew(
            price: String = "$3",
            showTime: String = "15:00 - 17:00"
        ): MovieCatalogInfoRequest =
            MovieCatalogInfoRequest(price, showTime)
    }
}