package com.movies.domain.exception

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.StringJoiner

@JsonIgnoreProperties(value = ["cause", "suppressed", "stackTrace", "localizedMessage"])
open class MoviesException(val errorCode: String) : RuntimeException()

@JsonInclude(JsonInclude.Include.NON_EMPTY)
open class BadRequestException(
    errorCode: String,
    override val message: String,
    val suggestion: String?
) : MoviesException(errorCode) {

    override fun toString() =
        StringJoiner(", ", BadRequestException::class.java.simpleName + "[", "]")
            .add("errorCode='$errorCode'")
            .add("message=$message")
            .add("suggestion=$suggestion")
            .toString()
}