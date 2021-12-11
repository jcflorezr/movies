package com.movies.application.service

import com.movies.adapters.daos.MovieDao
import com.movies.adapters.daos.row.MovieRow
import com.movies.adapters.rest.client.ombd.OMDbClient
import com.movies.domain.entity.Movie
import org.springframework.stereotype.Service

interface MovieService {

    fun findByMovieId(movieId: String): Movie?
}

@Service
class MovieServiceImpl(
    private val movieDao: MovieDao,
    private val omDbClient: OMDbClient
) : MovieService {

    override fun findByMovieId(movieId: String): Movie? =
        movieDao.findByImdbId(movieId)?.toEntity()
            ?: omDbClient.retrieveMovie(movieId).toEntity()
                .let { movieEntity ->
                    movieDao.save(MovieRow.fromEntity(movieEntity))
                    movieEntity
                }

}