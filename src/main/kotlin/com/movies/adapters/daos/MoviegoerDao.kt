package com.movies.adapters.daos

import com.movies.adapters.daos.row.MoviegoerRow
import org.springframework.data.jpa.repository.JpaRepository

interface MoviegoerDao : JpaRepository<MoviegoerRow, String> {
}