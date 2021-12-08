package com.movies.adapters.daos.row

import com.movies.domain.entity.Movie
import com.movies.domain.entity.Rating
import com.movies.domain.vo.StringVO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "MOVIE")
class MovieRow(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "TITLE") val title: String,
    @Column(name = "YEAR") val year: String,
    @Column(name = "RATED") val rated: String,
    @Column(name = "RELEASED") val released: String,
    @Column(name = "RUNTIME") val runtime: String,
    @Column(name = "GENRE") val genre: String,
    @Column(name = "DIRECTOR") val director: String,
    @Column(name = "WRITER") val writer: String,
    @Column(name = "ACTORS") val actors: String,
    @Column(name = "PLOT") val plot: String,
    @Column(name = "LANGUAGE") val language: String,
    @Column(name = "COUNTRY") val country: String,
    @Column(name = "POSTER") val poster: String,
    @Column(name = "IMDB_ID") val imdbID: String,
    @Column(name = "IMDB_RATING") val imdbRating: String,
    @Column(name = "PRODUCTION") val production: String,
    @Column(name = "WEBSITE") val website: String,
    @OneToMany
    @JoinColumn(name = "RATING_ID", referencedColumnName = "ID")
    val ratings: List<RatingRow>
) {

    companion object {
        fun fromEntity(movie: Movie): MovieRow =
            movie.run {
                MovieRow(
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
                    ratings = ratings.map { RatingRow.fromEntity(it) }
                )
            }
    }

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

@Entity
@Table(name = "RATING")
class RatingRow(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "SOURCE") val source: String,
    @Column(name = "VALUE") val value: String
) {

    companion object {
        fun fromEntity(rating: Rating): RatingRow =
            rating.run {
                RatingRow(
                    source = source.value,
                    value = value.value
                )
            }
    }

    fun toEntity() =
        Rating(
            source = StringVO(source, 5, 100, "ratingSource"),
            value = StringVO(value, 5, 100, "ratingValue")
        )
}