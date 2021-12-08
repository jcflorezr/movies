package com.movies.adapters.rest.api.model.response

import com.movies.domain.entity.Movie
import com.movies.domain.entity.Rating

data class MovieResponse(
    val title: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val language: String,
    val country: String,
    val poster: String,
    val imdbID: String,
    val imdbRating: String,
    val production: String,
    val website: String,
    val ratings: List<RatingResponse>
) {
    companion object {
        fun fromEntity(movie: Movie): MovieResponse =
            movie.run {
                MovieResponse(
                    title = title.value,
                    year = year.value,
                    rated = rated.value,
                    released = released.value,
                    runtime = runtime.value,
                    genre = genre.value,
                    director = director.value,
                    writer = writer.value,
                    actors = actors.value,
                    plot = plot.value,
                    language = language.value,
                    country = country.value,
                    poster = poster.value,
                    imdbID = imdbID.value,
                    imdbRating = imdbRating.value,
                    production = production.value,
                    website = website.value,
                    ratings = ratings.map { RatingResponse.fromEntity(it) }
                )
            }
    }
}

data class RatingResponse(
    val source: String,
    val value: String
) {

    companion object {
        fun fromEntity(rating: Rating): RatingResponse =
            rating.run {
                RatingResponse(
                    source = source.value,
                    value = value.value
                )
            }
    }
}