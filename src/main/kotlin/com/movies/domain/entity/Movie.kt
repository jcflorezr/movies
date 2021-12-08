package com.movies.domain.entity

import com.movies.domain.vo.StringVO

data class Movie(
    val title: StringVO,
    val year: StringVO,
    val rated: StringVO,
    val released: StringVO,
    val runtime: StringVO,
    val genre: StringVO,
    val director: StringVO,
    val writer: StringVO,
    val actors: StringVO,
    val plot: StringVO,
    val language: StringVO,
    val country: StringVO,
    val poster: StringVO,
    val imdbRating: StringVO,
    val imdbID: StringVO,
    val production: StringVO,
    val website: StringVO,
    val ratings: List<Rating>
)

data class Rating(
    val source: StringVO,
    val value: StringVO
)