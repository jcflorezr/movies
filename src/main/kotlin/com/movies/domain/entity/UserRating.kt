package com.movies.domain.entity

import com.movies.domain.vo.StringVO

data class UserRating(
    val moviegoer: Moviegoer,
    val rating: StringVO,
    val comments: StringVO,
    val movie: Movie
)