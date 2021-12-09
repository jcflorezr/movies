package com.movies.adapters.rest.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.movies.adapters.rest.api.model.response.MovieResponse
import com.movies.application.service.MovieService
import com.movies.domain.entity.dummy.DummyMovie
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringJUnitConfig
@WebMvcTest(controllers = [MovieController::class])
internal class MovieControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var movieService: MovieService

    private val objectMapper: ObjectMapper = ObjectMapper().registerKotlinModule()

    @Nested
    @DisplayName("GET /api/v1/movies/{movieId}")
    inner class RetrieveMovies {

        @Test
        @DisplayName("Should return Movie info and HTTP 200")
        fun findMovieByMovieId() {
            val movieId = "tt0232500"

            val movieToReturn = DummyMovie.createNew()
            `when`(movieService.findByMovieId(movieId)).thenReturn(movieToReturn)

            val mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/movies/$movieId")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

            val actualResponse = objectMapper.readValue<MovieResponse>(mvcResult.response.contentAsString)
            val expectedResponse = MovieResponse.fromEntity(movieToReturn)

            assertThat(actualResponse).isEqualTo(expectedResponse)
        }

        @Test
        @DisplayName("Movie not found and HTTP 404")
        fun movieNotFound() {
            val movieId = "tt0232500"

            `when`(movieService.findByMovieId(movieId)).thenReturn(null)

            mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/movies/$movieId")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
        }
    }
}