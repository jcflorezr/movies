package com.movies.adapters.daos

import com.movies.adapters.daos.row.MovieRow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MovieDao : JpaRepository<MovieRow, Int> {

    @Query("SELECT m FROM MovieRow m WHERE m.imdbId = :movieId")
    fun findByImdbId(@Param("movieId") movieId: String): MovieRow?
}