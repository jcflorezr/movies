package com.movies.adapters.rest.client.ombd

import com.movies.adapters.rest.client.ombd.model.MovieOMDbResponse
import com.movies.domain.exception.MoviesException
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.DefaultUriBuilderFactory
import java.net.URI
import javax.annotation.PostConstruct

@Service
class OMDbClient(
    builder: RestTemplateBuilder
) {

    private val restTemplate: RestTemplate = builder.build()

    @Value("\${client.omdb.url}")
    private lateinit var oMDbUrl: String

    @Value("\${client.omdb.api-key}")
    private lateinit var oMDbApiKey: String

    @PostConstruct
    fun configureRestTemplate() {
        restTemplate.uriTemplateHandler = DefaultUriBuilderFactory(oMDbUrl)
    }

    fun retrieveMovie(movieId: String): MovieOMDbResponse {
        return restTemplate.exchange(
            RequestEntity<MovieOMDbResponse>(HttpMethod.GET, URI.create("?i=$movieId&$oMDbApiKey")),
            MovieOMDbResponse::class.java
        ).body ?: throw MoviesException("No body found in OMDb API response.")
    }

}