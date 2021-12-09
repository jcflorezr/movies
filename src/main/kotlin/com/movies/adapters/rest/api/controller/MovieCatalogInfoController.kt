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
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class MovieCatalogInfoController(
    private val movieCatalogInfoService: MovieCatalogInfoService,
    private val movieService: MovieService
) {

    @ResponseBody
    @GetMapping(value = ["/movies/{movieId}/catalog-info"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findCatalogInfoByMovieId(@PathVariable(value = "movieId") movieId: String): ResponseEntity<MovieCatalogInfoResponse> {
        return movieCatalogInfoService.findByMovieId(movieId)?.let { catalog ->
            ResponseEntity.ok(MovieCatalogInfoResponse.fromEntity(catalog))
        } ?: ResponseEntity.notFound().build()
    }

    @ResponseBody
    @PostMapping(value = ["/movies/{movieId}/catalog-info"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveCatalogInfo(
        @PathVariable(value = "movieId") movieId: String,
        @RequestBody movieCatalogInfoRequest: MovieCatalogInfoRequest
    ): ResponseEntity<Map<String, String>> {
        return movieService.findByMovieId(movieId)?.let { movie ->
            movieCatalogInfoService.saveMovieCatalogInfo(movieCatalogInfoRequest.toEntity(movie))
            ResponseEntity.ok(mapOf("result" to "SUCCESS"))
        } ?: ResponseEntity.notFound().build()
    }

    @ResponseBody
    @PutMapping(value = ["/movies/{movieId}/catalog-info"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateCatalogInfo(
        @PathVariable(value = "movieId") movieId: String,
        @RequestBody movieCatalogInfoRequest: MovieCatalogInfoRequest
    ): ResponseEntity<MovieCatalogInfoResponse> {
        return movieService.findByMovieId(movieId)?.let { movie ->
            val movieCatalogInfo = movieCatalogInfoRequest.toEntity(movie)
            movieCatalogInfoService.saveMovieCatalogInfo(movieCatalogInfo)
            ResponseEntity.ok(MovieCatalogInfoResponse.fromEntity(movieCatalogInfo))
        } ?: ResponseEntity.notFound().build()
    }
}