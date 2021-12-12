package com.movies.application.service

import com.movies.adapters.daos.MoviegoerDao
import com.movies.adapters.daos.row.MoviegoerRow
import com.movies.domain.entity.Moviegoer
import org.springframework.stereotype.Service

interface MoviegoerService {

    fun saveMoviegoer(moviegoer: Moviegoer)

    fun findMoviegoer(moviegoerId: String): Moviegoer?
}

@Service
class MoviegoerServiceImpl(
    private val moviegoerDao: MoviegoerDao
) : MoviegoerService {

    override fun saveMoviegoer(moviegoer: Moviegoer) {
        moviegoerDao.save(MoviegoerRow.fromEntity(moviegoer))
    }

    override fun findMoviegoer(moviegoerId: String): Moviegoer? =
        moviegoerDao.findById(moviegoerId)
            .map { row -> row.toEntity() }
            .orElse(null)

}