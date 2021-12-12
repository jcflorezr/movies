package com.movies.adapters.rest.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.movies.adapters.rest.api.model.request.dummy.DummyMoviegoerRequest
import com.movies.adapters.rest.api.model.request.dummy.DummyUserRatingRequest
import com.movies.application.service.MovieService
import com.movies.application.service.MoviegoerService
import com.movies.application.service.UserRatingService
import com.movies.domain.entity.dummy.DummyMovie
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
@WebMvcTest(controllers = [UserRatingController::class])
internal class UserRatingControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userRatingService: UserRatingService
    @MockBean
    private lateinit var movieService: MovieService
    @MockBean
    private lateinit var moviegoerService: MoviegoerService

    private val objectMapper: ObjectMapper = ObjectMapper()

    @Nested
    @DisplayName("POST /api/v1/movies/{movieId}/ratings")
    inner class PostUserRatings {

        @Test
        @DisplayName("Should save User Rating and return HTTP 200")
        fun saveUserRating() {
            val movieId = "tt0232500"
            val dummyRequest = DummyUserRatingRequest.createNew()
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            val movieToReturn = DummyMovie.createNew()
            `when`(movieService.findByMovieId(movieId)).thenReturn(movieToReturn)
            val moviegoerToReturn = DummyMoviegoerRequest.createNew().toEntity()
            `when`(moviegoerService.findMoviegoer(moviegoerToReturn.userName.value)).thenReturn(moviegoerToReturn)

            mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/movies/$movieId/ratings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
        }

        @Test
        @DisplayName("Should return HTTP 400 due to empty Moviegoer name")
        fun emptyMoviegoerName() {
            val movieId = "tt0232500"
            val dummyRequest = DummyUserRatingRequest.createNew(moviegoerId = "")
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            val movieToReturn = DummyMovie.createNew()
            `when`(movieService.findByMovieId(movieId)).thenReturn(movieToReturn)

            mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/movies/$movieId/ratings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
        }

        @Test
        @DisplayName("Movie not found and HTTP 404")
        fun movieNotFound() {
            val movieId = "tt0232500"
            val dummyRequest = DummyUserRatingRequest.createNew(moviegoerId = "")
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            `when`(movieService.findByMovieId(movieId)).thenReturn(null)

            mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/movies/$movieId/ratings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
        }
    }
}