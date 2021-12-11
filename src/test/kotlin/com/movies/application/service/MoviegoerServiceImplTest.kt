package com.movies.application.service

import com.movies.adapters.config.PostgreSqlInitializer
import com.movies.adapters.daos.MoviegoerDao
import com.movies.adapters.rest.api.model.request.dummy.DummyMoviegoerRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(initializers = [PostgreSqlInitializer::class])
@SpringBootTest
internal class MoviegoerServiceImplTest {

    @Autowired
    private lateinit var moviegoerService: MoviegoerService
    @Autowired
    private lateinit var moviegoerDao: MoviegoerDao

    @Test
    @DisplayName("Store a Moviegoer in db and then retrieve it from db")
    fun saveMoviegoer() {
        val newMoviegoer = DummyMoviegoerRequest.createNew().toEntity()
        moviegoerService.saveMoviegoer(newMoviegoer)
        val actualMoviegoerName = moviegoerDao.findById(newMoviegoer.userName.value)
            .map { it.userName }
            .orElseThrow { RuntimeException("no moviegoer found") }
        assertThat(actualMoviegoerName).isEqualTo("Moviegoer1")
    }
}