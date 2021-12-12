package com.movies.adapters.configuration

import com.fasterxml.jackson.annotation.JsonInclude
import com.movies.domain.exception.DataValidationException

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorResponse(val errorMessage: Any) {

    companion object {

        fun of(ex: Exception): ErrorResponse {
            return ErrorResponse(errorMessage = ex)
        }
    }
}
