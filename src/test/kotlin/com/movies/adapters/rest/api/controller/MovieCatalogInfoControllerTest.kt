package com.movies.adapters.rest.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.movies.adapters.rest.api.model.request.dummy.DummyMovieCatalogInfoRequest
import com.movies.adapters.rest.api.model.response.MovieCatalogInfoResponse
import com.movies.application.service.MovieCatalogInfoService
import com.movies.application.service.MovieService
import com.movies.domain.entity.dummy.DummyMovie
import org.assertj.core.api.Assertions
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
@WebMvcTest(controllers = [MovieCatalogInfoController::class])
internal class MovieCatalogInfoControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var movieCatalogInfoService: MovieCatalogInfoService
    @MockBean
    private lateinit var movieService: MovieService

    private val objectMapper: ObjectMapper = ObjectMapper().registerKotlinModule()

    @Nested
    @DisplayName("POST /api/v1/movies/{movieId}/catalog-info")
    inner class PostCatalogInfo {

        @Test
        @DisplayName("Should save Movie Catalog and return HTTP 200")
        fun saveCatalogInfo() {
            val movieId = "tt0232500"
            val dummyRequest = DummyMovieCatalogInfoRequest.createNew()
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            val movieToReturn = DummyMovie.createNew()
            `when`(movieService.findByMovieId(movieId)).thenReturn(movieToReturn)

            mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/movies/$movieId/catalog-info")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
        }

        @Test
        @DisplayName("Movie not found and return HTTP 404")
        fun movieNotFound() {
            val movieId = "tt0232500"
            val dummyRequest = DummyMovieCatalogInfoRequest.createNew()
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            `when`(movieService.findByMovieId(movieId)).thenReturn(null)

            mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/movies/$movieId/catalog-info")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
                .andReturn()
        }
    }

    @Nested
    @DisplayName("GET /api/v1/movies/{movieId}/catalog-info")
    inner class GetCatalogInfo {

        @Test
        @DisplayName("Should retrieve Movie Catalog and return HTTP 200")
        fun findCatalogInfoByMovieId() {
            val movieId = "tt0232500"

            val movieCatalogToReturn = DummyMovieCatalogInfoRequest.createNew().toEntity(DummyMovie.createNew())
            `when`(movieCatalogInfoService.findByMovieId(movieId)).thenReturn(movieCatalogToReturn)

            val mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/movies/$movieId/catalog-info")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()

            val actualResponse = objectMapper.readValue<MovieCatalogInfoResponse>(mvcResult.response.contentAsString)
            val expectedResponse = MovieCatalogInfoResponse.fromEntity(movieCatalogToReturn)

            Assertions.assertThat(actualResponse).isEqualTo(expectedResponse)
        }

        @Test
        @DisplayName("Movie Catalog not found and return HTTP 404")
        fun movieCatalogNotFound() {
            val movieId = "tt0232500"

            `when`(movieCatalogInfoService.findByMovieId(movieId)).thenReturn(null)

            mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/movies/$movieId/catalog-info")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
        }
    }

    @Nested
    @DisplayName("PUT /api/v1/movies/{movieId}/catalog-info")
    inner class PutCatalogInfo {

        @Test
        @DisplayName("Should retrieve Movie Catalog and return HTTP 200")
        fun updateCatalogInfo() {
            val movieId = "tt0232500"
            val dummyRequest = DummyMovieCatalogInfoRequest.createNew()
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            val movieToReturn = DummyMovie.createNew()
            `when`(movieService.findByMovieId(movieId)).thenReturn(movieToReturn)
            val movieCatalogToReturn = DummyMovieCatalogInfoRequest.createNew().toEntity(DummyMovie.createNew())
            `when`(movieCatalogInfoService.findByMovieId(movieId)).thenReturn(movieCatalogToReturn)

            val mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/movies/$movieId/catalog-info")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()

            val actualResponse = objectMapper.readValue<MovieCatalogInfoResponse>(mvcResult.response.contentAsString)
            val expectedResponse = MovieCatalogInfoResponse.fromEntity(movieCatalogToReturn)

            Assertions.assertThat(actualResponse).isEqualTo(expectedResponse)
        }

        @Test
        @DisplayName("Movie not found and return HTTP 404")
        fun movieNotFound() {
            val movieId = "tt0232500"
            val dummyRequest = DummyMovieCatalogInfoRequest.createNew()
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            `when`(movieService.findByMovieId(movieId)).thenReturn(null)

            mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/movies/$movieId/catalog-info")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
        }
    }
}