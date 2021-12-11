package com.movies.adapters.daos.row

import com.movies.domain.entity.MovieCatalogInfo
import com.movies.domain.vo.StringVO
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "MOVIE_CATALOG_INFO")
class MovieCatalogInfoRow (
    @Id
    val id: Int? = null,
    @Column(name = "PRICE") val price: String? = null,
    @Column(name = "SHOW_TIME") val showTime: String? = null,
    @OneToOne
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "IMDB_ID")
    val movie: MovieRow? = null
) {

    companion object {
        fun fromEntity(movieCatalogInfo: MovieCatalogInfo): MovieCatalogInfoRow =
            movieCatalogInfo.run {
                MovieCatalogInfoRow(
                    id = Random().nextInt(10000),
                    price = price.value,
                    showTime = showTime.value,
                    movie = MovieRow.fromEntity(movie)
                )
            }
    }

    fun toEntity() =
        MovieCatalogInfo(
            price = StringVO(price!!, 1, 500, "moviePrice"),
            showTime = StringVO(showTime!!, 1, 500, "movieShowTime"),
            movie = movie!!.toEntity()
        )
}