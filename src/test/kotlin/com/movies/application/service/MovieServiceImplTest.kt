package com.movies.application.service

import com.movies.adapters.config.PostgreSqlInitializer
import com.movies.adapters.daos.MovieDao
import com.movies.adapters.daos.row.MovieRow
import com.movies.domain.entity.dummy.DummyMovie
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(initializers = [PostgreSqlInitializer::class])
@SpringBootTest
internal class MovieServiceImplTest {

    @Autowired
    private lateinit var movieService: MovieService
    @Autowired
    private lateinit var movieDao: MovieDao

    @Test
    @DisplayName("Store a Movie in db and then retrieve it from db")
    fun findByMovieId() {
        val movieToSave = MovieRow.fromEntity(DummyMovie.createNew(imdbId = "tt0232501"))
        movieDao.save(movieToSave)
        movieService.findByMovieId(movieToSave.imdbId!!)
            ?.let { actualMovie -> assertThat(actualMovie).isEqualTo(movieToSave.toEntity()) }
            ?: throw RuntimeException("no Movie found")
    }
}