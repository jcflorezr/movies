package com.movies.domain.vo

import com.movies.domain.exception.DataValidationException
import org.apache.commons.validator.GenericValidator

abstract class GenericVO(
    val value: String,
    private val minLength: Int,
    private val maxLength: Int,
    private val fieldName: String,
    private val errorMessage: String? = null,
    private val regex: String? = null
) {

    companion object {
        private const val EMPTY_ERROR_MESSAGE = "value is empty"
    }

    protected fun validateIfEmpty() =
        value.takeIf { it.isNotBlank() }
            ?: throw DataValidationException(fieldName, value, errorMessage ?: EMPTY_ERROR_MESSAGE)

    protected fun validateValueLength() {
        val lengthErrorMessage = "value is less than $minLength characters or greater than $maxLength characters."
        value
            .takeIf { it.isNotBlank() }
            .takeIf { GenericValidator.minLength(value, minLength) }
            .takeIf { GenericValidator.maxLength(value, maxLength) }
            ?: throw DataValidationException(fieldName, value, errorMessage ?: lengthErrorMessage)
    }

    protected fun validateOptionalValueLength() {
        if (value.isNotBlank()) {
            val lengthErrorMessage = "value is less than $minLength characters or greater than $maxLength characters."
            value.takeIf { GenericValidator.minLength(value, minLength) }
                .takeIf { GenericValidator.maxLength(value, maxLength) }
                ?: throw DataValidationException(fieldName, value, errorMessage ?: lengthErrorMessage)
        }
    }

    protected fun applyRegexPattern() {
        val regexErrorMessage = "value does not comply the format: $regex."
        value
            .takeIf { it.isNotBlank() }
            .takeIf { GenericValidator.matchRegexp(value, regex) }
            ?: throw DataValidationException(fieldName, value, errorMessage ?: regexErrorMessage)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GenericVO

        if (value != other.value) return false
        if (minLength != other.minLength) return false
        if (maxLength != other.maxLength) return false
        if (fieldName != other.fieldName) return false
        if (errorMessage != other.errorMessage) return false
        if (regex != other.regex) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + minLength
        result = 31 * result + maxLength
        result = 31 * result + fieldName.hashCode()
        result = 31 * result + (errorMessage?.hashCode() ?: 0)
        result = 31 * result + (regex?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "GenericStringVO(value=$value, minLength=$minLength, maxLength=$maxLength, fieldName=$fieldName, errorMessage=$errorMessage, regex=$regex)"
    }
}