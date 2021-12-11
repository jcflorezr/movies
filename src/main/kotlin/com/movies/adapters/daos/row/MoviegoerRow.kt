package com.movies.adapters.daos.row

import com.movies.domain.entity.Moviegoer
import com.movies.domain.vo.StringVO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "MOVIEGOER")
class MoviegoerRow (
    @Id
    @Column(name = "USER_NAME")
    val userName: String? = null
) {

    companion object {

        fun fromEntity(moviegoer: Moviegoer): MoviegoerRow = MoviegoerRow(userName = moviegoer.userName.value)
    }

    fun toEntity(): Moviegoer =
        Moviegoer(
            userName = StringVO(
                value = userName!!,
                minLength = 2,
                maxLength = 500,
                fieldName = "moviegoerName"
            )
        )
}