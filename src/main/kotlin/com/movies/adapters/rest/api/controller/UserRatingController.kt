package com.movies.adapters.rest.api.controller

import com.movies.adapters.rest.api.model.request.UserRatingRequest
import com.movies.application.service.MovieService
import com.movies.application.service.UserRatingService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserRatingController(
    private val userRatingService: UserRatingService,
    private val movieService: MovieService
) {

    @ResponseBody
    @PostMapping(value = ["/movies/{movieId}/ratings"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveUserRating(
        @PathVariable(value = "movieId") movieId: String,
        @RequestBody userRatingRequest: UserRatingRequest
    ): ResponseEntity<Map<String, String>> {
        val movie = movieService.findByMovieId(movieId)
        userRatingService.saveUserRating(userRatingRequest.toEntity(movie))
        return ResponseEntity.ok(mapOf("result" to "SUCCESS"))
    }

}