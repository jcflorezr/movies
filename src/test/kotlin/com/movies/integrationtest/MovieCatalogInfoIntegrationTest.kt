package com.movies.integrationtest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.movies.adapters.config.PostgreSqlInitializer
import com.movies.adapters.daos.MovieDao
import com.movies.adapters.daos.row.MovieRow
import com.movies.adapters.rest.api.model.request.dummy.DummyMovieCatalogInfoRequest
import com.movies.adapters.rest.api.model.response.MovieCatalogInfoResponse
import com.movies.domain.entity.dummy.DummyMovie
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@ContextConfiguration(initializers = [PostgreSqlInitializer::class])
@SpringBootTest
@AutoConfigureMockMvc
internal class MovieCatalogInfoIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var movieDao: MovieDao

    private val objectMapper: ObjectMapper = ObjectMapper().registerKotlinModule()

    @Nested
    @DisplayName("POST /api/v1/movies/{movieId}/catalog-info")
    inner class SaveMoveInfoCatalog {

        @Test
        @DisplayName("Should save Movie Catalog Info and return HTTP 200")
        fun saveMoveInfoCatalog() {
            val movieId = "tt0322259"
            val dummyRequest = DummyMovieCatalogInfoRequest.createNew()
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/movies/$movieId/catalog-info")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
        }
    }

    @Nested
    @DisplayName("PUT /api/v1/movies/{movieId}/catalog-info")
    inner class PutCatalogInfo {

        @Test
        @DisplayName("Should retrieve Movie Catalog and return HTTP 200")
        fun updateCatalogInfo() {
            val movieId = "tt5433138"
            val dummyRequest = DummyMovieCatalogInfoRequest.createNew()
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            // Create Movie
            val movie = DummyMovie.createNew(imdbId = movieId)
            val movieToSave = MovieRow.fromEntity(movie)
            movieDao.save(movieToSave)

            val movieCatalogToReturn = DummyMovieCatalogInfoRequest.createNew().toEntity(movie)

            val mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/movies/$movieId/catalog-info")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload)
            )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()

            val actualResponse = objectMapper.readValue<MovieCatalogInfoResponse>(mvcResult.response.contentAsString)
            val expectedResponse = MovieCatalogInfoResponse.fromEntity(movieCatalogToReturn)

            Assertions.assertThat(actualResponse).isEqualTo(expectedResponse)
        }
    }
}