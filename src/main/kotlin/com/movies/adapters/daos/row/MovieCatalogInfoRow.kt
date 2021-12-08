package com.movies.adapters.daos.row

import com.movies.domain.entity.MovieCatalogInfo
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
@Table(name = "MOVIE_CATALOG_INFO")
class MovieCatalogInfoRow (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "PRICE") val price: String,
    @Column(name = "SHOW_TIME") val showTime: String,
    @OneToOne
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")
    val movie: MovieRow
) {

    companion object {
        fun fromEntity(movieCatalogInfo: MovieCatalogInfo): MovieCatalogInfoRow =
            movieCatalogInfo.run {
                MovieCatalogInfoRow(
                    price = price.value,
                    showTime = showTime.value,
                    movie = MovieRow.fromEntity(movie)
                )
            }
    }

    fun toEntity() =
        MovieCatalogInfo(
            price = StringVO(price, 1, 10, "moviePrice"),
            showTime = StringVO(showTime, 1, 10, "movieShowTime"),
            movie = movie.toEntity()
        )
}