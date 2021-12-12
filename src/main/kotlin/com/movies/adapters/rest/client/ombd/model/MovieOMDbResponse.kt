package com.movies.adapters.rest.client.ombd.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.movies.domain.entity.Movie
import com.movies.domain.entity.Rating
import com.movies.domain.vo.StringVO

data class MovieOMDbResponse(
    @JsonProperty("Title") val title: String,
    @JsonProperty("Year") val year: String,
    @JsonProperty("Rated") val rated: String,
    @JsonProperty("Released") val released: String,
    @JsonProperty("Runtime") val runtime: String,
    @JsonProperty("Genre") val genre: String,
    @JsonProperty("Director") val director: String,
    @JsonProperty("Writer") val writer: String,
    @JsonProperty("Actors") val actors: String,
    @JsonProperty("Plot") val plot: String,
    @JsonProperty("Language") val language: String,
    @JsonProperty("Country") val country: String,
    @JsonProperty("Awards") val awards: String,
    @JsonProperty("Poster") val poster: String,
    @JsonProperty("Ratings") val ratings: List<RatingResponse>,
    @JsonProperty("Metascore") val metaScore: String,
    @JsonProperty("imdbRating") val imdbRating: String,
    @JsonProperty("imdbVotes") val imdbVotes: String,
    @JsonProperty("imdbID") val imdbId: String,
    @JsonProperty("Type") val type: String,
    @JsonProperty("DVD") val dvd: String,
    @JsonProperty("BoxOffice") val boxOffice: String,
    @JsonProperty("Production") val production: String,
    @JsonProperty("Website") val website: String,
    @JsonProperty("Response") val response: String
) {
    fun toEntity() =
        Movie(
            title = StringVO(title, 1, 500, "title"),
            year = StringVO(year, 1, 500, "year"),
            rated = StringVO(rated, 1, 500, "rated"),
            released = StringVO(released, 1, 500, "released"),
            runtime = StringVO(runtime, 1, 500, "runtime"),
            genre = StringVO(genre, 1, 500, "genre"),
            director = StringVO(director, 1, 500, "director"),
            writer = StringVO(writer, 1, 500, "writer"),
            actors = StringVO(actors, 1, 500, "actors"),
            plot = StringVO(plot, 1, 500, "plot"),
            language = StringVO(language, 1, 500, "language"),
            country = StringVO(country, 1, 500, "country"),
            poster = StringVO(poster, 1, 500, "poster"),
            imdbId = StringVO(imdbId, 1, 500, "imdbID"),
            imdbRating = StringVO(imdbRating, 1, 500, "imdbRating"),
            production = StringVO(production, 1, 500, "production"),
            website = StringVO(website, 1, 500, "website"),
            ratings = ratings.map { it.toEntity() }
        )
}

data class RatingResponse(
    @JsonProperty("Source") val source: String,
    @JsonProperty("Value") val value: String
) {
    fun toEntity() =
        Rating(
            source = StringVO(source, 1, 100, "ratingSource"),
            value = StringVO(value, 1, 100, "ratingValue")
        )
}
