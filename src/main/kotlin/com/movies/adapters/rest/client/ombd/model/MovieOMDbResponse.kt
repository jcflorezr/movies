package com.movies.adapters.rest.client.ombd.model

import com.movies.domain.entity.Movie
import com.movies.domain.entity.Rating
import com.movies.domain.vo.StringVO

data class MovieOMDbResponse(
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
    val awards: String,
    val poster: String,
    val ratings: List<RatingResponse>,
    val metaScore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val imdbID: String,
    val type: String,
    val dvd: String,
    val boxOffice: String,
    val production: String,
    val website: String,
    val response: String
) {
    fun toEntity() =
        Movie(
            title = StringVO(title, 1, 10, "title"),
            year = StringVO(year, 1, 10, "year"),
            rated = StringVO(rated, 1, 10, "rated"),
            released = StringVO(released, 1, 10, "released"),
            runtime = StringVO(runtime, 1, 10, "runtime"),
            genre = StringVO(genre, 1, 10, "genre"),
            director = StringVO(director, 1, 10, "director"),
            writer = StringVO(writer, 1, 10, "writer"),
            actors = StringVO(actors, 1, 10, "actors"),
            plot = StringVO(plot, 1, 10, "plot"),
            language = StringVO(language, 1, 10, "language"),
            country = StringVO(country, 1, 10, "country"),
            poster = StringVO(poster, 1, 10, "poster"),
            imdbID = StringVO(imdbID, 1, 10, "imdbID"),
            imdbRating = StringVO(imdbRating, 1, 10, "imdbRating"),
            production = StringVO(production, 1, 10, "production"),
            website = StringVO(website, 1, 10, "website"),
            ratings = ratings.map { it.toEntity() }
        )
}

data class RatingResponse(
    val source: String,
    val value: String
) {
    fun toEntity() =
        Rating(
            source = StringVO(source, 5, 100, "ratingSource"),
            value = StringVO(value, 5, 100, "ratingValue")
        )
}
