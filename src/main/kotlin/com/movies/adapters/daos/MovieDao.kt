package com.movies.adapters.daos

import com.movies.adapters.daos.row.MovieRow
import com.movies.adapters.daos.row.RatingRow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MovieDao : JpaRepository<MovieRow, String> {

    @Query("SELECT m FROM MovieRow m WHERE m.imdbId = :movieId")
    fun findByImdbId(@Param("movieId") movieId: String): MovieRow?
}

interface RatingDao : JpaRepository<RatingRow, Int> {

    @Query("SELECT r FROM RatingRow r WHERE r.movieId.id = :movieId")
    fun findMovieId(@Param("movieId") movieId: String): RatingRow?
}