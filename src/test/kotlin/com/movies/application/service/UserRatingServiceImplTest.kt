package com.movies.application.service

import com.movies.adapters.config.PostgreSqlInitializer
import com.movies.adapters.daos.MovieDao
import com.movies.adapters.daos.MoviegoerDao
import com.movies.adapters.daos.UserRatingDao
import com.movies.adapters.daos.row.MovieRow
import com.movies.adapters.daos.row.MoviegoerRow
import com.movies.adapters.daos.row.UserRatingRow
import com.movies.adapters.rest.api.model.request.dummy.DummyMoviegoerRequest
import com.movies.adapters.rest.api.model.request.dummy.DummyUserRatingRequest
import com.movies.domain.entity.dummy.DummyMovie
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(initializers = [PostgreSqlInitializer::class])
@SpringBootTest
internal class UserRatingServiceImplTest {

    @Autowired
    private lateinit var userRatingService: UserRatingService
    @Autowired
    private lateinit var userRatingDao: UserRatingDao
    @Autowired
    private lateinit var movieDao: MovieDao
    @Autowired
    lateinit var moviegoerDao: MoviegoerDao

    @Test
    @DisplayName("Store a Movie User Rating in db and then retrieve it from db")
    fun saveUserRating() {
        // Create Movie
        val movie = DummyMovie.createNew(imdbId = "tt0232503")
        val movieToSave = MovieRow.fromEntity(movie)
        movieDao.save(movieToSave)

        // Create Moviegoer
        val moviegoerToSave = MoviegoerRow.fromEntity(DummyMoviegoerRequest.createNew().toEntity())
        moviegoerDao.save(moviegoerToSave)

        val userRatingToSave = DummyUserRatingRequest.createNew().toEntity(movie)
        userRatingService.saveUserRating(userRatingToSave)
        userRatingDao.findByMovieId(movie.imdbId.value)
            ?.let { actualUserRating -> assertThat(actualUserRating.toEntity()).isEqualTo(userRatingToSave) }
            ?: throw RuntimeException("no User Rating found")
    }
}