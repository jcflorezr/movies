package com.movies.adapters.rest.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.movies.adapters.rest.api.model.request.dummy.DummyMoviegoerRequest
import com.movies.application.service.MoviegoerService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringJUnitConfig
@WebMvcTest(controllers = [MoviegoerController::class])
internal class MoviegoerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var moviegoerService: MoviegoerService

    private val objectMapper: ObjectMapper = ObjectMapper()

    @Nested
    @DisplayName("POST /api/v1/moviegoers")
    inner class PostMoviegoers {

        @Test
        @DisplayName("Should save Moviegoer and return HTTP 200")
        fun saveMoviegoer() {
            val dummyRequest = DummyMoviegoerRequest.createNew()
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            mockMvc.perform(
                post("/api/v1/moviegoers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value("SUCCESS"))
        }

        @Test
        @DisplayName("Should return HTTP 400 due to empty Moviegoer name")
        fun emptyMoviegoerName() {
            val dummyRequest = DummyMoviegoerRequest.createNew(moviegoerName = "")
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            mockMvc.perform(
                post("/api/v1/moviegoers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                .andExpect(status().isBadRequest)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMessage.errorCode").value("invalid_field_value"))
                .andExpect(jsonPath("$.errorMessage.message").value("value is empty"))
                .andExpect(jsonPath("$.errorMessage.fieldName").value("userName"))
        }

        @Test
        @DisplayName("Should return HTTP 400 due to invalid Moviegoer name")
        fun invalidMoviegoerName() {
            val invalidMoviegoerName = "Moviegoeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeer"
            val dummyRequest = DummyMoviegoerRequest.createNew(moviegoerName = invalidMoviegoerName)
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            mockMvc.perform(
                post("/api/v1/moviegoers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                .andExpect(status().isBadRequest)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMessage.errorCode").value("invalid_field_value"))
                .andExpect(jsonPath("$.errorMessage.message").value("value is less than 1 characters or greater than 50 characters."))
                .andExpect(jsonPath("$.errorMessage.fieldName").value("userName"))
        }
    }
}