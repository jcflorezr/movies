package com.movies.adapters.daos

import com.movies.adapters.daos.row.UserRatingRow
import org.springframework.data.jpa.repository.JpaRepository

interface UserRatingDao : JpaRepository<UserRatingRow, Int> {
}