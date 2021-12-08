package com.movies.adapters.daos.row

import com.movies.domain.entity.UserRating
import com.movies.domain.vo.StringVO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "USER_RATING")
class UserRatingRow(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "RATING") val rating: String,
    @Column(name = "COMMENTS") val comments: String,
    @OneToOne
    @JoinColumn(name = "MOVIEGOER_NAME", referencedColumnName = "USER_NAME")
    val moviegoer: MoviegoerRow,
    @OneToOne
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")
    val movie: MovieRow
) {

    companion object {
        fun fromEntity(userRating: UserRating): UserRatingRow = userRating.run {
            UserRatingRow(
                rating = rating.value,
                comments = comments.value,
                moviegoer = MoviegoerRow.fromEntity(moviegoer),
                movie = MovieRow.fromEntity(movie)
            )
        }
    }

    fun toEntity() = UserRating(
        rating = StringVO(rating, 1, 10, "userRating"),
        comments = StringVO(comments, 1, 500, "userComments"),
        moviegoer = moviegoer.toEntity(),
        movie = movie.toEntity()
    )
}