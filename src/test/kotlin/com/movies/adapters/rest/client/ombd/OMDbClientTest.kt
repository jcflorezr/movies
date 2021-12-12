package com.movies.adapters.rest.client.ombd

import com.fasterxml.jackson.databind.ObjectMapper
import com.movies.adapters.rest.client.ombd.model.MovieOMDbResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.core.io.Resource
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.client.ExpectedCount
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers
import org.springframework.test.web.client.response.MockRestResponseCreators

@RestClientTest(
    value = [OMDbClient::class],
    properties = [
        "client.omdb.url=http://localhost:8080",
        "client.omdb.api-key=omdb-api-key"
    ]
)
internal class OMDbClientTest {

    @Value("\${client.omdb.url}")
    private lateinit var omdbUrl: String

    @Value("\${client.omdb.api-key}")
    private lateinit var oMDbApiKey: String

    @Value("classpath:rest/client/omdb/omdb-success-response.json")
    private lateinit var omdbSuccessResponseFile: Resource

    private val objectMapper: ObjectMapper = ObjectMapper()

    @Autowired
    private lateinit var mockServer: MockRestServiceServer

    @Autowired
    private lateinit var omdbClient: OMDbClient

    @Test
    @DisplayName("Retrieve movie from OMDb")
    fun retrieveMovie() {
        val movieId = "tt0232500"

        mockServer.expect(
            ExpectedCount.once(),
            MockRestRequestMatchers.requestTo("$omdbUrl?i=$movieId&apikey=$oMDbApiKey")
        )
        .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
        .andRespond(MockRestResponseCreators.withSuccess(omdbSuccessResponseFile, MediaType.APPLICATION_JSON))

        val actualOMDbResponse: MovieOMDbResponse = omdbClient.retrieveMovie(movieId)
        val expectedOneLiveResponse: MovieOMDbResponse = objectMapper.readValue(
            omdbSuccessResponseFile.url,
            MovieOMDbResponse::class.java
        )

        assertThat(actualOMDbResponse).isEqualTo(expectedOneLiveResponse)
    }
}