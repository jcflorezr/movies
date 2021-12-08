package com.movies.domain.entity

import com.movies.domain.vo.StringVO

data class MovieCatalogInfo (
    val movie: Movie,
    val price: StringVO,
    val showTime: StringVO
)