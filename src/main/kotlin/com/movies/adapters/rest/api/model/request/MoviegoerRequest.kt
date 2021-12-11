package com.movies.adapters.rest.api.model.request

import com.movies.domain.entity.Moviegoer
import com.movies.domain.entity.UserRating
import com.movies.domain.vo.StringVO

data class MoviegoerRequest(
    val userName: String
) {

    fun toEntity() =
        Moviegoer(userName = StringVO(userName, 2, 500, "moviegoerName"))
}