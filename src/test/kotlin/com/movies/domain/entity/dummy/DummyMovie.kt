package com.movies.domain.entity.dummy

import com.movies.domain.entity.Movie
import com.movies.domain.entity.Rating
import com.movies.domain.vo.StringVO

class DummyMovie {

    companion object {
        fun createNew(
            title: String = "The Fast and the Furious",
            year: String = "2001",
            rated: String = "PG-13",
            released: String = "22 Jun 2001",
            runtime: String = "106 min",
            genre: String = "Action, Crime, Thriller",
            director: String = "Rob Cohen",
            writer: String = "Ken Li, Gary Scott Thompson, Erik Bergquist",
            actors: String = "Vin Diesel, Paul Walker, Michelle Rodriguez",
            plot: String = "Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.",
            language: String = "English, Spanish",
            country: String = "United States, Germany",
            poster: String = "https://m.media-amazon.com/images/M/MV5BNzlkNzVjMDMtOTdhZC00MGE1LTkxODctMzFmMjkwZmMxZjFhXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
            imdbRating: String = "6.8",
            imdbId: String = "tt0232500",
            production: String = "N/A",
            website: String = "N/A",
            ratings: List<Rating> = listOf(
                DummyRating.createNew(source = "Internet Movie Database", value = "6.8/10"),
                DummyRating.createNew(source = "Rotten Tomatoes", value = "54%"),
                DummyRating.createNew(source = "Metacritic", value = "58/100")
            )
        ): Movie =
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
                ratings = ratings)
    }
}

class DummyRating {

    companion object {
        fun createNew(
            source: String = "Rotten Tomatoes",
            value: String = "5/10"
        ): Rating =
            Rating(
                StringVO(source, 1, 500, "ratingSource"),
                StringVO(value, 1, 500, "ratingValue")
            )
    }
}