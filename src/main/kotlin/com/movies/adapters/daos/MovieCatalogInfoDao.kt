package com.movies.adapters.daos

import com.movies.adapters.daos.row.MovieCatalogInfoRow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MovieCatalogInfoDao : JpaRepository<MovieCatalogInfoRow, Int> {

    @Query("SELECT m FROM MovieCatalogInfoRow m WHERE m.movieId = :movieId")
    fun findByMovieId(@Param("movieId") movieId: String): MovieCatalogInfoRow
}