package com.movies.adapters.daos.row

import com.movies.domain.entity.UserRating
import com.movies.domain.vo.StringVO
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "USER_RATING")
class UserRatingRow(
    @Id
    val id: Int? = null,
    @Column(name = "RATING") val rating: String? = null,
    @Column(name = "COMMENTS") val comments: String? = null,
    @OneToOne
    @JoinColumn(name = "MOVIEGOER_NAME", referencedColumnName = "USER_NAME")
    val moviegoer: MoviegoerRow? = null,
    @OneToOne
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "IMDB_ID")
    val movie: MovieRow? = null
) {

    companion object {
        fun fromEntity(userRating: UserRating): UserRatingRow = userRating.run {
            UserRatingRow(
                id = Random().nextInt(10000),
                rating = rating.value,
                comments = comments.value,
                moviegoer = MoviegoerRow(userName = moviegoer.userName.value),
                movie = MovieRow(imdbId = userRating.movie.imdbId.value)
            )
        }
    }

    fun toEntity() =
        UserRating(
        rating = StringVO(rating!!, 1, 500, "userRating"),
        comments = StringVO(comments!!, 1, 500, "userComments"),
        moviegoer = moviegoer!!.toEntity(),
        movie = movie!!.toEntity()
    )
}