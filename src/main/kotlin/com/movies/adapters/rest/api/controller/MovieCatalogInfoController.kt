package com.movies.adapters.rest.api.controller

import com.movies.adapters.rest.api.model.request.MovieCatalogInfoRequest
import com.movies.adapters.rest.api.model.response.MovieCatalogInfoResponse
import com.movies.application.service.MovieCatalogInfoService
import com.movies.application.service.MovieService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

class MovieCatalogInfoController(
    private val movieCatalogInfoService: MovieCatalogInfoService,
    private val movieService: MovieService
) {

    @ResponseBody
    @GetMapping(value = ["/movies/{movieId}/catalog-info"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findCatalogInfoByMovieId(@PathVariable(value = "movieId") movieId: String): ResponseEntity<MovieCatalogInfoResponse> {
        val movie = movieCatalogInfoService.findByMovieId(movieId)
        return ResponseEntity.ok(MovieCatalogInfoResponse.fromEntity(movie))
    }

    @ResponseBody
    @PostMapping(value = ["/movies/{movieId}/catalog-info"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveCatalogInfo(
        @PathVariable(value = "movieId") movieId: String,
        @RequestBody movieCatalogInfoRequest: MovieCatalogInfoRequest
    ): ResponseEntity<Map<String, String>> {
        val movie = movieService.findByMovieId(movieId)
        movieCatalogInfoService.saveMovieCatalogInfo(movieCatalogInfoRequest.toEntity(movie))
        return ResponseEntity.ok(mapOf("result" to "SUCCESS"))
    }

    @ResponseBody
    @PutMapping(value = ["/movies/{movieId}/catalog-info"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateCatalogInfo(
        @PathVariable(value = "movieId") movieId: String,
        @RequestBody movieCatalogInfoRequest: MovieCatalogInfoRequest
    ): ResponseEntity<MovieCatalogInfoResponse> {
        val movie = movieService.findByMovieId(movieId)
        val updatedMovieCatalogInfo =
            movieCatalogInfoService.updateMovieCatalogInfo(movieCatalogInfoRequest.toEntity(movie))
        return ResponseEntity.ok(MovieCatalogInfoResponse.fromEntity(updatedMovieCatalogInfo))
    }
}