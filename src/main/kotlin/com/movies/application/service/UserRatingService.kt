package com.movies.application.service

import com.movies.adapters.daos.UserRatingDao
import com.movies.adapters.daos.row.UserRatingRow
import com.movies.domain.entity.UserRating
import org.springframework.stereotype.Service

interface UserRatingService {

    fun saveUserRating(userRating: UserRating)
}

@Service
class UserRatingServiceImpl(
    private val userRatingDao: UserRatingDao
) : UserRatingService {

    override fun saveUserRating(userRating: UserRating) {
        userRatingDao.save(UserRatingRow.fromEntity(userRating))
    }

}