package com.movies.adapters.daos

import com.movies.adapters.daos.row.UserRatingRow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRatingDao : JpaRepository<UserRatingRow, Int> {

    @Query("SELECT u FROM UserRatingRow u WHERE u.movie.imdbId = :movieId")
    fun findByMovieId(@Param("movieId") movieId: String): UserRatingRow?
}