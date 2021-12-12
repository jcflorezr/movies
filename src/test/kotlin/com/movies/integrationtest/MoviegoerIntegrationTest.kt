package com.movies.integrationtest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.movies.adapters.config.PostgreSqlInitializer
import com.movies.adapters.rest.api.model.request.dummy.DummyMoviegoerRequest
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
internal class MoviegoerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val objectMapper: ObjectMapper = ObjectMapper().registerKotlinModule()

    @Nested
    @DisplayName("POST /api/v1/moviegoers")
    inner class SaveMoviegoers {

        @Test
        @DisplayName("Should save Moviegoer and return HTTP 200")
        fun saveMoviegoer() {
            val dummyRequest = DummyMoviegoerRequest.createNew()
            val requestPayload: String = objectMapper.writeValueAsString(dummyRequest)

            mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/moviegoers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("SUCCESS"))
        }
    }
}