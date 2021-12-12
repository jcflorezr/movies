package com.movies.adapters.rest.client.ombd

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.movies.adapters.rest.client.ombd.model.MovieOMDbResponse
import com.movies.domain.exception.MoviesException
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
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

    private val mapper: ObjectMapper = ObjectMapper().registerKotlinModule()

    @PostConstruct
    fun configureRestTemplate() {
        restTemplate.uriTemplateHandler = DefaultUriBuilderFactory(oMDbUrl)
    }

    fun retrieveMovie(movieId: String): MovieOMDbResponse {
        return restTemplate.getForEntity(URI.create("$oMDbUrl?i=$movieId&apikey=$oMDbApiKey"), String::class.java)
            .let { response -> mapper.readTree(response.body) }
            .takeIf { response -> response.get("Response").asText() == "True" }
            ?.let { response -> mapper.treeToValue(response, MovieOMDbResponse::class.java) }
            ?: throw MoviesException("No body found in OMDb API response.")
    }

}