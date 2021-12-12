package com.movies.integrationtest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.movies.adapters.config.PostgreSqlInitializer
import com.movies.adapters.rest.api.model.response.MovieResponse
import com.movies.domain.entity.dummy.DummyMovie
import com.movies.domain.entity.dummy.DummyRating
import org.assertj.core.api.Assertions.assertThat
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
internal class MovieIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val objectMapper: ObjectMapper = ObjectMapper().registerKotlinModule()

    @Nested
    @DisplayName("GET /api/v1/movies/{movieId}")
    inner class RetrieveMovies {

        @Test
        @DisplayName("Should return Movie info and HTTP 200")
        fun findMovieByMovieId() {
            val movieId = "tt0463985"

            val movieToReturn = DummyMovie.createNew(
                title="The Fast and the Furious: Tokyo Drift",
                year="2006",
                rated="PG-13",
                released="16 Jun 2006",
                runtime="104 min",
                genre="Action, Crime, Thriller",
                director="Justin Lin",
                writer="Chris Morgan",
                actors="Lucas Black, Zachery Ty Bryan, Shad Moss",
                plot="A teenager becomes a major competitor in the world of drift racing after moving in with his father in Tokyo to avoid a jail sentence in America.",
                language="English, Japanese, Portuguese",
                country="United States, Germany, Japan",
                poster="https://m.media-amazon.com/images/M/MV5BMTQ2NTMxODEyNV5BMl5BanBnXkFtZTcwMDgxMjA0MQ@@._V1_SX300.jpg",
                imdbId="tt0463985",
                imdbRating="6.0",
                production="N/A",
                website="N/A",
                ratings= listOf(
                    DummyRating.createNew(source="Internet Movie Database", value="6.0/10"),
                    DummyRating.createNew(source="Rotten Tomatoes", value="38%"),
                    DummyRating.createNew(source="Metacritic", value="45/100"))
            )

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
    }
}