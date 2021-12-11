package com.movies.application.service

import com.movies.adapters.config.PostgreSqlInitializer
import com.movies.adapters.daos.MovieDao
import com.movies.adapters.daos.row.MovieRow
import com.movies.adapters.rest.api.model.request.dummy.DummyMovieCatalogInfoRequest
import com.movies.domain.entity.dummy.DummyMovie
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(initializers = [PostgreSqlInitializer::class])
@SpringBootTest
internal class MovieCatalogInfoServiceImplTest {

    @Autowired
    private lateinit var movieCatalogInfoService: MovieCatalogInfoService
    @Autowired
    private lateinit var movieDao: MovieDao

    @Test
    @DisplayName("Store information of a Movie Catalog in db and then retrieve it from db")
    fun saveMovieCatalogInfo() {
        // Create Movie
        val movie = DummyMovie.createNew(imdbId = "tt0232502")
        val movieToSave = MovieRow.fromEntity(movie)
        movieDao.save(movieToSave)

        val catalogInfoToSave = DummyMovieCatalogInfoRequest.createNew().toEntity(movie)
        movieCatalogInfoService.saveMovieCatalogInfo(catalogInfoToSave)
        movieCatalogInfoService.findByMovieId(movie.imdbId.value)
            ?.let { actualMovieCatalog -> Assertions.assertThat(actualMovieCatalog).isEqualTo(catalogInfoToSave) }
            ?: throw RuntimeException("no Movie Catalog found")
    }
}