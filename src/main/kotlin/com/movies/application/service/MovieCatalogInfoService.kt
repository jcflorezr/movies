package com.movies.application.service

import com.movies.adapters.daos.MovieCatalogInfoDao
import com.movies.adapters.daos.row.MovieCatalogInfoRow
import com.movies.domain.entity.MovieCatalogInfo
import org.springframework.stereotype.Service

interface MovieCatalogInfoService {

    fun findByMovieId(movieId: String): MovieCatalogInfo?
    fun saveMovieCatalogInfo(movieCatalog: MovieCatalogInfo)
    fun updateMovieCatalogInfo(movieCatalog: MovieCatalogInfo): MovieCatalogInfo
}

@Service
class MovieCatalogInfoServiceImpl(
    private val movieCatalogInfoDao: MovieCatalogInfoDao
) : MovieCatalogInfoService {

    override fun findByMovieId(movieId: String): MovieCatalogInfo? =
        movieCatalogInfoDao.findByMovieId(movieId)?.toEntity()

    override fun saveMovieCatalogInfo(movieCatalog: MovieCatalogInfo) {
        movieCatalogInfoDao.save(MovieCatalogInfoRow.fromEntity(movieCatalog))
    }

    override fun updateMovieCatalogInfo(movieCatalog: MovieCatalogInfo): MovieCatalogInfo =
        movieCatalogInfoDao.save(MovieCatalogInfoRow.fromEntity(movieCatalog)).toEntity()

}