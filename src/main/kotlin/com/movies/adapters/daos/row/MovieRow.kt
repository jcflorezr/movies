package com.movies.adapters.daos.row

import com.movies.domain.entity.Movie
import com.movies.domain.entity.Rating
import com.movies.domain.vo.StringVO
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "MOVIE")
class MovieRow(
    @Id
    @Column(name = "IMDB_ID")
    val imdbId: String? = null,
    @Column(name = "TITLE") val title: String? = null,
    @Column(name = "MOVIE_YEAR") val year: String? = null,
    @Column(name = "RATED") val rated: String? = null,
    @Column(name = "RELEASED") val released: String? = null,
    @Column(name = "RUNTIME") val runtime: String? = null,
    @Column(name = "GENRE") val genre: String? = null,
    @Column(name = "DIRECTOR") val director: String? = null,
    @Column(name = "WRITER") val writer: String? = null,
    @Column(name = "ACTORS") val actors: String? = null,
    @Column(name = "PLOT") val plot: String? = null,
    @Column(name = "MOVIE_LANGUAGE") val language: String? = null,
    @Column(name = "COUNTRY") val country: String? = null,
    @Column(name = "POSTER") val poster: String? = null,
    @Column(name = "IMDB_RATING") val imdbRating: String? = null,
    @Column(name = "PRODUCTION") val production: String? = null,
    @Column(name = "WEBSITE") val website: String? = null,
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movieId", cascade = [CascadeType.ALL])
    val ratings: List<RatingRow>? = null
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
                    imdbId = imdbId.value,
                    imdbRating = imdbRating.value,
                    production = production.value,
                    website = website.value,
                    ratings = ratings.map { RatingRow.fromEntity(it, movie) }
                )
            }
    }

    fun toEntity() =
        Movie(
            title = StringVO(title!!, 1, 500, "title"),
            year = StringVO(year!!, 1, 500, "year"),
            rated = StringVO(rated!!, 1, 500, "rated"),
            released = StringVO(released!!, 1, 500, "released"),
            runtime = StringVO(runtime!!, 1, 500, "runtime"),
            genre = StringVO(genre!!, 1, 500, "genre"),
            director = StringVO(director!!, 1, 500, "director"),
            writer = StringVO(writer!!, 1, 500, "writer"),
            actors = StringVO(actors!!, 1, 500, "actors"),
            plot = StringVO(plot!!, 1, 500, "plot"),
            language = StringVO(language!!, 1, 500, "language"),
            country = StringVO(country!!, 1, 500, "country"),
            poster = StringVO(poster!!, 1, 500, "poster"),
            imdbId = StringVO(imdbId!!, 1, 500, "imdbID"),
            imdbRating = StringVO(imdbRating!!, 1, 500, "imdbRating"),
            production = StringVO(production!!, 1, 500, "production"),
            website = StringVO(website!!, 1, 500, "website"),
            ratings = ratings!!.map { it.toEntity() }
        )
}

@Entity
@Table(name = "RATING")
class RatingRow(
    @Id
    val id: Int? = null,
    @Column(name = "SOURCE") val source: String? = null,
    @Column(name = "RATING_VALUE") val value: String? = null,
    @ManyToOne
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "IMDB_ID")
    val movieId: MovieRow? = null
) {

    companion object {
        fun fromEntity(rating: Rating, movie: Movie): RatingRow =
            rating.run {
                RatingRow(
                    id = Random().nextInt(10000),
                    source = source.value,
                    value = value.value,
                    movieId = MovieRow(imdbId = movie.imdbId.value)
                )
            }
    }

    fun toEntity() =
        Rating(
            source = StringVO(source!!, 1, 500, "ratingSource"),
            value = StringVO(value!!, 1, 500, "ratingValue")
        )
}