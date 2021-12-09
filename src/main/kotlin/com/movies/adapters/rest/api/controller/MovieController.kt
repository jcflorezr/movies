package com.movies.adapters.rest.api.controller

import com.movies.adapters.rest.api.model.response.MovieResponse
import com.movies.application.service.MovieService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class MovieController(
    private val movieService: MovieService
) {

    @ResponseBody
    @GetMapping(value = ["/movies/{movieId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findMovieByMovieId(@PathVariable(value = "movieId") movieId: String): ResponseEntity<MovieResponse> {
        val movie = movieService.findByMovieId(movieId)
        return ResponseEntity.ok(MovieResponse.fromEntity(movie))
    }

}