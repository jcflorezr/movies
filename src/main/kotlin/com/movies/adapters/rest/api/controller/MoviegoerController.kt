package com.movies.adapters.rest.api.controller

import com.movies.adapters.rest.api.model.request.MoviegoerRequest
import com.movies.application.service.MoviegoerService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class MoviegoerController(
    private val moviegoerService: MoviegoerService
) {

    @ResponseBody
    @PostMapping(value = ["/moviegoers"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveMoviegoer(@RequestBody moviegoerRequest: MoviegoerRequest): ResponseEntity<Map<String, String>> {
        moviegoerService.saveMoviegoer(moviegoerRequest.toEntity())
        return ResponseEntity.ok(mapOf("result" to "SUCCESS"))
    }
}