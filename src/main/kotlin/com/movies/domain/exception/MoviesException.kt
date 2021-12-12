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

@JsonInclude(JsonInclude.Include.NON_EMPTY)
open class InternalServerErrorException(
    errorCode: String = "internal_error",
    private val throwable: Throwable
) : MoviesException(errorCode) {

    override val message: String? = throwable.message ?: throwable.localizedMessage
    private val simplifiedStackTrace: List<SimplifiedStackTraceElement>

    init {
        simplifiedStackTrace = generateSimplifiedStackTrace(throwable.stackTrace)
    }

    private fun generateSimplifiedStackTrace(
        stackTraceElements: Array<StackTraceElement>
    ) = stackTraceElements.asSequence().groupByTo(
        destination = LinkedHashMap(),
        keySelector = { it.className },
        valueTransform = { it.lineNumber }
    ).map { SimplifiedStackTraceElement(it.key, it.value) }

    override fun toString(): String {
        return "InternalServerErrorException(ex=$throwable, message=$message, simplifiedStackTrace=$simplifiedStackTrace)"
    }

    data class SimplifiedStackTraceElement(
        val className: String,
        val lines: List<Int>
    )
}